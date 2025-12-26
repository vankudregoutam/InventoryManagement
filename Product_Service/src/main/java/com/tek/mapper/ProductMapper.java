package com.tek.mapper;

import com.tek.entity.Product;
import com.tek.entity.dto.ProductCreateDTO;
import com.tek.entity.dto.ProductResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toProduct(ProductCreateDTO createDTO);

    ProductResponseDTO toResponseDTO(Product product);
}
