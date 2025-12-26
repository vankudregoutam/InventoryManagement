package com.tek.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq_gen")
    @SequenceGenerator(name = "product_seq_gen", initialValue = 201, allocationSize = 1)
    private Long id;

    @Column(unique = true)
    private String productName;
    private BigDecimal price;
    private Boolean available;
}
