package com.tek.entity;

import jakarta.persistence.*;
import jdk.jshell.Snippet;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "paymentId")
    @TableGenerator(
            name = "paymentId",
            table = "payment_sequence",
            pkColumnName = "seq_name",
            valueColumnName = "seq_value",
            pkColumnValue = "payment_seq",
            initialValue = 401,
            allocationSize = 1
    )
    private Long paymentId;
    private Long orderId;
    private Double amount;
    @Enumerated(EnumType.STRING)
    private Status status;
    private LocalDate createdDate;
}
