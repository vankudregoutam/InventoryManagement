package com.tek.entity.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class InventoryAddDTO {
    @NotNull(message = "Product ID is required")
    @Positive(message = "Product ID must be a positive value")
    private Long productId;

    @NotNull(message = "Available stocks cannot be null")
    @Min(value = 0, message = "Available stocks cannot be negative")
    private Integer availableStocks;
}
