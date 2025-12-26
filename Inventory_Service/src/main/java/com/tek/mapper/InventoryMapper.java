package com.tek.mapper;

import com.tek.entity.Inventory;
import com.tek.entity.dto.InventoryAddDTO;
import com.tek.entity.dto.InventoryResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InventoryMapper {
    Inventory toInventory(InventoryAddDTO addDTO);

    InventoryResponseDTO toResponseDto(Inventory inventory);
}
