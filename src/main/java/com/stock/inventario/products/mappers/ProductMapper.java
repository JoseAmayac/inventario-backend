package com.stock.inventario.products.mappers;

import com.stock.inventario.products.dto.BasicProductDTO;
import com.stock.inventario.products.dto.ProductCreationDTO;
import com.stock.inventario.products.models.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel =  "spring")
public interface ProductMapper {
    Product toEntity(ProductCreationDTO productCreationDTO);
    @Mapping(source = "supplier", target = "supplier")
    BasicProductDTO toBasicDto(Product product);
}
