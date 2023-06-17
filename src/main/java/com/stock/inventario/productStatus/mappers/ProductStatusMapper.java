package com.stock.inventario.productStatus.mappers;

import com.stock.inventario.productStatus.dto.BasicProductStatusDTO;
import com.stock.inventario.productStatus.dto.ProductStatusCreationDTO;
import com.stock.inventario.productStatus.models.ProductStatus;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductStatusMapper {
    ProductStatus toEntity(ProductStatusCreationDTO productStatusCreationDTO);
    BasicProductStatusDTO toBasicDTO(ProductStatus productStatus);
}
