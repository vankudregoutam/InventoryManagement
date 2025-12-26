package com.tek.service.impl;

import com.tek.client.ProductClient;
import com.tek.entity.Inventory;
import com.tek.entity.dto.InventoryAddDTO;
import com.tek.entity.dto.InventoryResponseDTO;
import com.tek.entity.dto.StockRequest;
import com.tek.exception.inventory.ProductNotFoundException;
import com.tek.mapper.InventoryMapper;
import com.tek.repository.InventoryRepo;
import com.tek.service.InventoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepo repo;

    private final ProductClient productClient;

    private final InventoryMapper mapper;

    @Override
    @Transactional
    public void deductStock(Long productId, Integer quantity) {
        StockRequest req = new StockRequest();
        req.setProductId(productId);
        req.setQuantity(quantity);
        reduceStock(req);
    }

    @Transactional
    public void reduceStock(StockRequest request) {
        Inventory inventory = repo.findByProductIdWithLock(request.getProductId())
                .orElseThrow(() -> new ProductNotFoundException("Inventory not found for Product: " + request.getProductId()));

        int currentStock = inventory.getAvailableStocks();

        if (currentStock < request.getQuantity()) {
            throw new RuntimeException("Stock not available. Current: " + currentStock + ", Requested: " + request.getQuantity());
        }

        inventory.setAvailableStocks(currentStock - request.getQuantity());
        repo.save(inventory);

        if (inventory.getAvailableStocks() <= 0) {
            updateProductStatus(request.getProductId(), false);
        }
    }

    @Override
    @Transactional
    public void rollBackStock(Long productId, Integer quantity) {
        StockRequest req = new StockRequest();
        req.setProductId(productId);
        req.setQuantity(quantity);
        restoreStock(req);
    }

    @Transactional
    public void restoreStock(StockRequest request) {
        Inventory inventory = repo.findByProductIdWithLock(request.getProductId())
                .orElseThrow(() -> new ProductNotFoundException("Inventory missing for rollback: " + request.getProductId()));

        inventory.setAvailableStocks(inventory.getAvailableStocks() + request.getQuantity());
        repo.save(inventory);

        if (inventory.getAvailableStocks() > 0) {
            updateProductStatus(request.getProductId(), true);
        }
    }

    private void updateProductStatus(Long productId, boolean status) {
        try {
            productClient.updateAvailability(productId, status);
        } catch (Exception e) {
            System.err.println("Warning: Could not update product availability status in Product Service.");
        }
    }

    @Override
    public void addProduct(InventoryAddDTO addDTO) {
        if (!Boolean.TRUE.equals(productClient.findProduct(addDTO.getProductId()))) {
            throw new ProductNotFoundException("Cannot create inventory. Product ID invalid.");
        }
        repo.save(mapper.toInventory(addDTO));
    }

    @Override
    public Boolean isProductExists(Long productId, Integer quantity) {
        return repo.existsByProductId(productId) && isSufficient(productId, quantity);
    }

    @Override
    public Boolean isSufficient(Long productId, Integer quantity) {
        Optional<Inventory> inventory = repo.findByProductId(productId);
        return inventory.filter(value -> value.getAvailableStocks() >= quantity).isPresent();
    }

    @Override
    public void updateStock(Long productId, Integer quantity) {
        Inventory inventory = repo.findByProductId(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product Not Found"));
        if (quantity < 0) throw new RuntimeException("Invalid quantity");
        inventory.setAvailableStocks(quantity+ inventory.getAvailableStocks());
        repo.save(inventory);
    }

    @Override
    public InventoryResponseDTO getInventory(Long productId) {
        Optional<Inventory> inventory = repo.findByProductId(productId);
        if (inventory.isEmpty()) throw new ProductNotFoundException("Product Not Found");
        return mapper.toResponseDto(inventory.get());
    }

    @Override
    public List<InventoryResponseDTO> getAll() {
        return repo.findAll().stream().map(mapper::toResponseDto).toList();
    }
}
