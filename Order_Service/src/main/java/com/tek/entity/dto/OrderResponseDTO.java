package com.tek.entity.dto;

import com.tek.entity.Status;
import lombok.Data;

import java.time.LocalDate;

@Data
public class OrderResponseDTO {
    private Long orderId;
    private Long productId;
    private Status status;
    private Integer quantity;
    private LocalDate createdDate;
}
