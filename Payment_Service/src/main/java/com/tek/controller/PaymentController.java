package com.tek.controller;

import com.tek.constants.PaymentConstants;
import com.tek.entity.Payment;
import com.tek.entity.dto.AddPaymentDTO;
import com.tek.entity.dto.ResponseDTO;
import com.tek.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payment")
public class PaymentController {
    private final PaymentService service;

    @PostMapping("/initiate")
    public ResponseEntity<String> initiatePayment(@RequestParam Long orderId, @RequestParam Double amount) {
        service.processPayment(orderId, amount);
        return ResponseEntity.ok("Transaction Processed");
    }

    @PostMapping("/addPayment")
    public ResponseEntity<ResponseDTO> addPayment(@RequestParam Long orderId, @RequestParam Double amount) {
        service.addPayment(orderId, amount);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDTO(PaymentConstants.PAYMENT_INITIATED_CODE, PaymentConstants.PAYMENT_INITIATED_MSG));
    }

    @PostMapping("/doPayment")
    public ResponseEntity<ResponseDTO> doPayment(@RequestParam Long orderId) {
        return service.doPayment(orderId);
    }
}
