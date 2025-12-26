package com.tek.client;

import com.tek.entity.dto.ResponseDTO;
import com.tek.entity.dto.StockRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "inventory-service")
public interface InventoryClient {
    @GetMapping("/api/inventory/isProductExists")
    Boolean isProductExists(@RequestParam Long productId, @RequestParam Integer quantity);

    @PatchMapping("/api/inventory/deductStock")
    ResponseDTO deductStock(@RequestParam Long productId, @RequestParam Integer quantity);

    @PatchMapping("/api/inventory/rollbackStock")
    ResponseDTO rollBackStock(@RequestParam Long productId, @RequestParam Integer quantity);

    @PostMapping("/api/inventory/reserve")
    void reserveStock(@RequestBody StockRequest request);

    @PostMapping("/api/inventory/cancel-reservation")
    void cancelReservation(@RequestBody StockRequest request);
}
