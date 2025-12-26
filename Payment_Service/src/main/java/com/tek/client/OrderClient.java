package com.tek.client;

import com.tek.entity.Status;
import com.tek.entity.dto.ResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "ORDER-SERVICE", path = "/api/orders")
public interface OrderClient {

    @GetMapping("/{orderId}/getProductId")
    Long getProductId(@PathVariable Long orderId);

    @GetMapping("/{orderId}/getQuantity")
    Integer getQuantity(@PathVariable Long orderId);

    @PatchMapping("/{orderId}/updateStatus/{status}")
    ResponseDTO updateStatus(
            @PathVariable Long orderId,
            @PathVariable Status status
    );

    @PatchMapping("/{orderId}/payment-callback")
    void sendCallback(@PathVariable("orderId") Long orderId, @RequestParam("status") String status);
}

