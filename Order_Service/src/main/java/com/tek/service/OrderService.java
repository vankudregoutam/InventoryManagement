package com.tek.service;

import com.tek.entity.Status;
import com.tek.entity.dto.AddOrderDTO;
import com.tek.entity.dto.OrderResponseDTO;

import java.util.List;

public interface OrderService {
    void createOrder(AddOrderDTO orderDTO);

    Boolean findOrder(Long orderId);

    Long getProductId(Long orderId);

    Integer getQuantity(Long orderId);

    void updateStatus(Long orderId, Status status);

    void processPaymentCallback(Long orderId, String status);

    List<OrderResponseDTO> getAllOrders();

    List<?> getProducts();
}
