package com.tek.entity.dto;

import lombok.Data;

@Data
public class StockRequest {
    private Long productId;
    private Integer quantity;
}