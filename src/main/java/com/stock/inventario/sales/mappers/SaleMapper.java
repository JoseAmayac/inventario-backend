package com.stock.inventario.sales.mappers;

import com.stock.inventario.sales.dto.BasicSaleDTO;
import com.stock.inventario.sales.dto.SaleCreationDTO;
import com.stock.inventario.sales.models.Sale;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SaleMapper {
    public Sale toEntity(SaleCreationDTO saleCreation);
    @Mapping(source = "client", target = "client")
    @Mapping(source = "salesman", target = "salesman")
    public BasicSaleDTO toBasicDTO(Sale sale);
}
