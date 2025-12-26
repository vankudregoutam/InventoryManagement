package com.tek.service;

import com.tek.entity.dto.ProductCreateDTO;
import com.tek.entity.dto.ProductResponseDTO;

import java.util.List;

public interface ProductService {
    void createProduct(ProductCreateDTO productCreateDTO);

    Boolean findProduct(Long productId);

    Double getPrice(Long productId);

    List<ProductResponseDTO> getProducts();

    void updateAvailability(Long id, Boolean available);
}
