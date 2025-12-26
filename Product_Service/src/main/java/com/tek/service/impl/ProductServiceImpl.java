package com.tek.service.impl;

import com.tek.entity.Product;
import com.tek.entity.dto.ProductCreateDTO;
import com.tek.entity.dto.ProductResponseDTO;
import com.tek.exception.product.ProductAlreadyExistsException;
import com.tek.exception.product.ProductNotExistsException;
import com.tek.mapper.ProductMapper;
import com.tek.repository.ProductRepo;
import com.tek.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepo repo;

    private final ProductMapper productMapper;

    @Override
    public void updateAvailability(Long id, Boolean available) {
        Product product = repo.findById(id)
                .orElseThrow(() -> new ProductNotExistsException("Product not found with ID: " + id));

        product.setAvailable(available);
        repo.save(product);
    }

    @Override
    public void createProduct(ProductCreateDTO productCreateDTO) {
        Boolean exists = repo.existsByProductName(productCreateDTO.getProductName());
        if (exists)
            throw new ProductAlreadyExistsException("Product Already Exists...");

        Product product = productMapper.toProduct(productCreateDTO);
        product.setAvailable(true);
        repo.save(product);
    }

    @Override
    public Boolean findProduct(Long productId) {
        return repo.existsById(productId);
    }

    @Override
    public Double getPrice(Long productId) {
        Product product = repo.findById(productId)
                .orElseThrow(() -> new ProductNotExistsException("Product Not Exists"));

        return product.getPrice() != null ? product.getPrice().doubleValue() : null;
    }

    @Override
    public List<ProductResponseDTO> getProducts() {
        List<Product> products = repo.findByAvailableTrue();
        return products.stream().map(productMapper::toResponseDTO).toList();
    }
}
