package com.tek.service;

import com.tek.entity.dto.ResponseDTO;
import org.springframework.http.ResponseEntity;

public interface PaymentService {
    ResponseEntity<ResponseDTO> doPayment(Long orderId);

    void addPayment(Long orderId, Double amount);

    void processPayment(Long orderId, Double amount);
}
