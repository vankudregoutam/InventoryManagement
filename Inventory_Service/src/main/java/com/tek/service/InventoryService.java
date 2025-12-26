package com.tek.service;

import com.tek.entity.dto.InventoryAddDTO;
import com.tek.entity.dto.InventoryResponseDTO;
import com.tek.entity.dto.StockRequest;

import java.util.List;

public interface InventoryService {
    void addProduct(InventoryAddDTO addDTO);

    Boolean isProductExists(Long productId, Integer quantity);

    Boolean isSufficient(Long productId, Integer quantity);

    void deductStock(Long productId, Integer quantity);

    void rollBackStock(Long productId, Integer quantity);

    List<InventoryResponseDTO> getAll();

    InventoryResponseDTO getInventory(Long productId);

    void updateStock(Long productId, Integer quantity);

    void reduceStock(StockRequest request);

    void restoreStock(StockRequest request);
}
