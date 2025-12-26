package com.tek.client;

import com.tek.entity.dto.ResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("product-service")
public interface ProductClient {
    @GetMapping("/api/products/getPrice")
    Double getPrice(@RequestParam("productId") Long productId);

    @GetMapping("/api/products/getProducts")
    ResponseDTO getProducts();
}
