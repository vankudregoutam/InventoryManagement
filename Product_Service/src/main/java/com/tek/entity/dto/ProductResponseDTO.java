package com.tek.entity.dto;

import lombok.Data;

@Data
public class ProductResponseDTO {
    private String productName;
    private Double price;
    private Boolean available;
}
