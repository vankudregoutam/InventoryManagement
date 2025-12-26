package com.tek.client;

import com.tek.entity.dto.ResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "PAYMENT-SERVICE", path = "/api/payment")
public interface PaymentClient {
    @PostMapping("/addPayment")
    ResponseDTO addPayment(@RequestParam Long orderId, @RequestParam Double amount);

    @PostMapping("/doPayment")
    ResponseDTO doPayment(@RequestParam Long orderId);

    @PostMapping("/initiate")
    String initiatePayment(@RequestParam Long orderId, @RequestParam Double amount);
}

