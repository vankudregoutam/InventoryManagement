package com.tek.entity.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class AddOrderDTO {
    @NotNull(message = "Product ID is required")
    @Positive(message = "Product ID must be a positive value")
    private Long productId;

    @NotNull(message = "Status is required")
    private Integer quantity;
}
