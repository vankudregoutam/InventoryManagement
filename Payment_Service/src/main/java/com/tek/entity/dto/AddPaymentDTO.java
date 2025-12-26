package com.tek.entity.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddPaymentDTO {
    @NotNull(message = "Order Id is required")
    private Long orderId;
    private Double amount;
    private Long productId;
    private Integer quantity;
}
