package com.tek.mapper;

import com.tek.entity.Orders;
import com.tek.entity.dto.AddOrderDTO;
import com.tek.entity.dto.OrderResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Orders toOrder(AddOrderDTO orderDTO);

    OrderResponseDTO toResponseDto(Orders order);
}
