package com.tek.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "inventorySeqGen")
    @TableGenerator(
            name = "inventorySeqGen",
            table = "inventory_sequence",
            pkColumnName = "seq_name",
            valueColumnName = "seq_value",
            pkColumnValue = "inventory_seq",
            initialValue = 101,
            allocationSize = 1
    )
    private Long inventoryId;

    private Long productId;
    private Integer availableStocks;
}
