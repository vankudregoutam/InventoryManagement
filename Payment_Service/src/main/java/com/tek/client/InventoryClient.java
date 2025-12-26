package com.tek.client;

import com.tek.entity.dto.ResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("inventory-service")
public interface InventoryClient {
    @PostMapping("/api/inventory/deductStock")
    ResponseEntity<ResponseDTO> deductStock(@RequestParam Long productId, @RequestParam Integer quantity);
}
