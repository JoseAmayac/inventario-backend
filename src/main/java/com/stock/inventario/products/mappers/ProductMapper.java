package com.stock.inventario.products.mappers;

import com.stock.inventario.products.dto.BasicProductDTO;
import com.stock.inventario.products.dto.ProductCreationDTO;
import com.stock.inventario.products.models.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel =  "spring")
public interface ProductMapper {
    Product toEntity(ProductCreationDTO productCreationDTO);
    BasicProductDTO toBasicDto(Product product);
}
