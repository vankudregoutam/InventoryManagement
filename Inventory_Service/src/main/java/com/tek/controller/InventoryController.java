package com.tek.controller;

import com.tek.constants.InventoryConstants;
import com.tek.entity.dto.InventoryAddDTO;
import com.tek.entity.dto.InventoryResponseDTO;
import com.tek.entity.dto.ResponseDTO;
import com.tek.entity.dto.StockRequest;
import com.tek.service.InventoryService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inventory")
public class InventoryController {
    private final InventoryService service;

    @Hidden
    @PostMapping("/reduce")
    public ResponseEntity<String> reduceStock(@RequestBody StockRequest request) {
        service.reduceStock(request);
        return ResponseEntity.ok("Stock reduced successfully");
    }

    @Hidden
    @PostMapping("/restore")
    public ResponseEntity<String> restoreStock(@RequestBody StockRequest request) {
        service.restoreStock(request);
        return ResponseEntity.ok("Stock restored successfully");
    }

    @PostMapping("/addProduct")
    public ResponseEntity<ResponseDTO> addProduct(@RequestBody InventoryAddDTO addDTO) {
        service.addProduct(addDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDTO(InventoryConstants.INVENTORY_CREATED_CODE, InventoryConstants.INVENTORY_CREATED_MSG));
    }

    @GetMapping("/isProductExists")
    public Boolean isProductExists(@RequestParam Long productId, @RequestParam Integer quantity) {
        return service.isProductExists(productId, quantity);
    }

    @PatchMapping("/deductStock")
    public ResponseEntity<ResponseDTO> deductStock(@RequestParam Long productId, @RequestParam Integer quantity) {
        service.deductStock(productId, quantity);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDTO(InventoryConstants.STOCK_DEDUCT_CODE, InventoryConstants.STOCK_DEDUCT_MSG));
    }

    @PatchMapping("/rollbackStock")
    public ResponseEntity<ResponseDTO> rollbackStock(@RequestParam Long productId, @RequestParam Integer quantity) {
        service.rollBackStock(productId, quantity);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDTO(InventoryConstants.STOCK_ROLLBACK_CODE, InventoryConstants.STOCK_ROLLBACK_MSG));
    }

    @PatchMapping("/updateStock")
    public ResponseEntity<ResponseDTO> updateStock(@RequestParam Long productId, @RequestParam Integer quantity) {
        service.updateStock(productId, quantity);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDTO(InventoryConstants.INVENTORY_UPDATED_CODE, InventoryConstants.INVENTORY_UPDATED_MSG));
    }

    @GetMapping("/getInventory/{productId}")
    public ResponseEntity<ResponseDTO> getInventory(@PathVariable Long productId) {
        InventoryResponseDTO responseDTO = service.getInventory(productId);
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(new ResponseDTO(InventoryConstants.INVENTORY_FETCHED_CODE, InventoryConstants.INVENTORY_FETCHED_MSG, responseDTO));
    }

    @GetMapping("/getAllInventory")
    public ResponseEntity<ResponseDTO> getAllInventory() {
        List<InventoryResponseDTO> dtos = service.getAll();
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(new ResponseDTO(InventoryConstants.INVENTORY_FETCHED_CODE, InventoryConstants.INVENTORY_FETCHED_MSG, dtos));
    }

    @PostMapping("/reserve")
    public ResponseEntity<ResponseDTO> reserveStock(@RequestBody StockRequest request) {
        service.deductStock(request.getProductId(), request.getQuantity());

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDTO(InventoryConstants.STOCK_DEDUCT_CODE, "Stock reserved successfully"));
    }
}
