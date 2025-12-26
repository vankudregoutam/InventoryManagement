package com.tek.service.impl;

import com.tek.client.InventoryClient;
import com.tek.client.PaymentClient;
import com.tek.client.ProductClient;
import com.tek.entity.Orders;
import com.tek.entity.Status;
import com.tek.entity.dto.AddOrderDTO;
import com.tek.entity.dto.OrderResponseDTO;
import com.tek.entity.dto.ResponseDTO;
import com.tek.entity.dto.StockRequest;
import com.tek.exception.order.OrderNotFoundException;
import com.tek.exception.order.ProductNotFoundException;
import com.tek.mapper.OrderMapper;
import com.tek.repository.OrderRepo;
import com.tek.service.OrderService;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepo repo;
    private final OrderMapper mapper;
    private final ProductClient productClient;
    private final InventoryClient inventoryClient;
    private final PaymentClient paymentClient;

    @Override
    public void createOrder(AddOrderDTO orderDTO) {

        Double price;
        try {
            price = productClient.getPrice(orderDTO.getProductId());
        } catch (FeignException.NotFound e) {
            throw new ProductNotFoundException("Product not found with ID: " + orderDTO.getProductId());
        }

        StockRequest stockRequest = new StockRequest();
        stockRequest.setProductId(orderDTO.getProductId());
        stockRequest.setQuantity(orderDTO.getQuantity());

        try {
            inventoryClient.reserveStock(stockRequest);
        } catch (Exception e) {
            throw new RuntimeException("Stock reservation failed: " + e.getMessage());
        }

        Orders order = mapper.toOrder(orderDTO);
        order.setStatus(Status.PENDING);
        order.setCreatedDate(LocalDate.now());

        Orders savedOrder = repo.saveAndFlush(order);

        try {
            double amount = price * orderDTO.getQuantity();
            paymentClient.addPayment(savedOrder.getOrderId(), amount);

            ResponseDTO response = paymentClient.doPayment(savedOrder.getOrderId());

            if (response != null && "PAY_200".equals(response.getStatusCode())) {
                savedOrder.setStatus(Status.CONFIRMED);
                repo.save(savedOrder);
            } else {
                rollbackOrder(savedOrder);
            }

        } catch (Exception ex) {
            System.err.println("Payment Communication Error: " + ex.getMessage());
            rollbackOrder(savedOrder);
        }
    }

    private void rollbackOrder(Orders order) {
        if (order.getStatus() == Status.CANCELED) return;

        try {
            StockRequest stockRequest = new StockRequest();
            stockRequest.setProductId(order.getProductId());
            stockRequest.setQuantity(order.getQuantity());

            inventoryClient.cancelReservation(stockRequest);

        } catch (Exception ex) {
            System.err.println("CRITICAL: Failed to rollback inventory for Order " + order.getOrderId() + ": " + ex.getMessage());
        }

        order.setStatus(Status.CANCELED);
        repo.save(order);
    }

    @Override
    public Boolean findOrder(Long orderId) {
        return repo.existsById(orderId);
    }

    @Override
    public Long getProductId(Long orderId) {
        return repo.findById(orderId)
                .map(Orders::getProductId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found for ID: " + orderId));
    }

    @Override
    public Integer getQuantity(Long orderId) {
        return repo.findById(orderId)
                .map(Orders::getQuantity)
                .orElseThrow(() -> new OrderNotFoundException("Order not found for ID: " + orderId));
    }

    @Override
    public void updateStatus(Long orderId, Status status) {
        Orders order = repo.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found for ID: " + orderId));
        order.setStatus(status);
        repo.save(order);
    }

    @Override
    public void processPaymentCallback(Long orderId, String status) {
        Orders order = repo.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found: " + orderId));

        if ("PAID".equalsIgnoreCase(status)) {
            if (order.getStatus() != Status.CONFIRMED) {
                order.setStatus(Status.CONFIRMED);
                repo.save(order);
            }
        } else {
            System.out.println("Payment Rejected for Order: " + orderId);
            rollbackOrder(order);
        }
    }

    @Override
    public List<OrderResponseDTO> getAllOrders() {
        List<Orders> orders = repo.findAll();
        return orders.stream().map(mapper::toResponseDto).toList();
    }

    @Override
    public List<?> getProducts() {
        try {
            ResponseDTO response = productClient.getProducts();

            if (response != null && response.getObject() != null) {
                return (List<?>) response.getObject();
            }
        } catch (Exception e) {
            System.err.println("Error fetching products: " + e.getMessage());
        }
        return List.of();
    }

}
