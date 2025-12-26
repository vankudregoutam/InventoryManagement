package com.tek.entity.dto;


import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ProductCreateDTO {
    @NotBlank(message = "Product name is required")
    @Size(min = 2, max = 100, message = "Product name must be between 2 and 100 characters")
    private String productName;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be greater than zero")
    private Double price;

    private Boolean available = false;
}
