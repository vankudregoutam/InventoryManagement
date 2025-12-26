package com.tek.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderId")
    @TableGenerator(
            name = "orderId",
            table = "order_sequence",
            pkColumnName = "seq_name",
            valueColumnName = "seq_value",
            pkColumnValue = "order_seq",
            initialValue = 301,
            allocationSize = 1
    )
    private Long orderId;
    private Long productId;
    private Integer quantity;
    @Enumerated(EnumType.STRING)
    private Status status;
    private LocalDate createdDate;
}
