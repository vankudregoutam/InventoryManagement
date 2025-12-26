package com.tek.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "product-service")
public interface ProductClient {
    @GetMapping("/api/products/findProduct")
    Boolean findProduct(@RequestParam("productId") Long productId);

    @PutMapping("/api/products/{productId}/availability")
    void updateAvailability(@PathVariable("productId") Long productId, @RequestParam("available") Boolean available);
}
