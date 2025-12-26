package com.tek.controller;

import com.tek.constants.ProductConstants;
import com.tek.entity.dto.ProductCreateDTO;
import com.tek.entity.dto.ProductResponseDTO;
import com.tek.entity.dto.ResponseDTO;
import com.tek.service.ProductService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService service;

    @Hidden
    @PutMapping("/{id}/availability")
    public ResponseEntity<Void> updateAvailability(@PathVariable Long id, @RequestParam Boolean available) {
        service.updateAvailability(id, available);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createProduct(@RequestBody ProductCreateDTO productCreateDTO) {
        service.createProduct(productCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDTO(ProductConstants.PRODUCT_CREATED_CODE, ProductConstants.PRODUCT_CREATED_MSG));
    }

    @GetMapping("/findProduct")
    public Boolean findProduct(@RequestParam Long productId) {
        return service.findProduct(productId);
    }

    @GetMapping("/getProducts")
    public ResponseEntity<ResponseDTO> getProducts() {
        List<?> response = service.getProducts();

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDTO("200", "Products fetched successfully", response));
    }

    @GetMapping("/getPrice")
    public Double getPrice(@RequestParam Long productId) {
        return service.getPrice(productId);
    }
}
