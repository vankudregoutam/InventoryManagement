package com.tek.repository;

import com.tek.entity.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    Boolean existsByProductName(@NotBlank(message = "Product name is required")
                         @Size(min = 2, max = 100, message = "Product name must be between 2 and 100 characters")
                         String productName);

    List<Product> findByAvailableTrue();
}
