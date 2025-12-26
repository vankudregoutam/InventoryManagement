package com.tek.entity.dto;

import lombok.Data;

@Data
public class InventoryResponseDTO {
    private Long inventoryId;
    private Long productId;
    private Integer availableStocks;
}
