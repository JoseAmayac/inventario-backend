package com.stock.inventario.productSales.mappers;

import com.stock.inventario.productSales.dto.BasicProductSaleDTO;
import com.stock.inventario.productSales.dto.ProductSaleCreationDTO;
import com.stock.inventario.productSales.models.ProductSale;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductSaleMapper {
    ProductSale toEntity(ProductSaleCreationDTO productSaleCreationDTO);
    BasicProductSaleDTO toBasicDTO(ProductSale productSale);
}
