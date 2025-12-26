package com.tek.controller;

import com.tek.client.ProductClient;
import com.tek.constants.OrderConstants;
import com.tek.entity.Status;
import com.tek.entity.dto.AddOrderDTO;
import com.tek.entity.dto.OrderResponseDTO;
import com.tek.entity.dto.ResponseDTO;
import com.tek.repository.OrderRepo;
import com.tek.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService service;

    private final ProductClient productClient;

    @PostMapping("/createOrder")
    public ResponseEntity<ResponseDTO> createOrder(@RequestBody AddOrderDTO orderDTO) {
        service.createOrder(orderDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDTO(OrderConstants.ORDER_CREATED_CODE, OrderConstants.ORDER_CREATED_MSG));
    }

    @PatchMapping("/{orderId}/payment-callback")
    public ResponseEntity<String> updatePaymentStatus(@PathVariable Long orderId, @RequestParam String status) {
        service.processPaymentCallback(orderId, status);
        return ResponseEntity.ok("Callback processed.");
    }

    @GetMapping("/findOrder/{orderId}")
    public Boolean findOrder(@PathVariable Long orderId) {
        return service.findOrder(orderId);
    }

    @GetMapping("/{orderId}/getProductId")
    public Long getProductId(@PathVariable Long orderId) {
        return service.getProductId(orderId);
    }

    @GetMapping("/{orderId}/getQuantity")
    public Integer getQuantity(@PathVariable Long orderId) {
        return service.getQuantity(orderId);
    }

    @PatchMapping("/api/orders/{orderId}/updateStatus/{status}")
    public ResponseEntity<Void> updateStatus(@PathVariable Long orderId, @PathVariable Status status) {
        service.updateStatus(orderId, status);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getAllOrders")
    public ResponseEntity<ResponseDTO> getAllOrders() {
        List<OrderResponseDTO> dtos = service.getAllOrders();
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(new ResponseDTO(OrderConstants.ORDER_FETCHED_CODE, OrderConstants.ORDER_FETCHED_MSG, dtos));
    }

    @GetMapping("/getProducts")
    public ResponseEntity<ResponseDTO> getProducts() {
        List<?> response = service.getProducts();

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDTO("200", "Products fetched successfully", response));
    }

}
