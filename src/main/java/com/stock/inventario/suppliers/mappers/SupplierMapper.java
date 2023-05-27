package com.stock.inventario.suppliers.mappers;

import com.stock.inventario.suppliers.dto.BasicSupplierDTO;
import com.stock.inventario.suppliers.dto.SupplierCreationDTO;
import com.stock.inventario.suppliers.models.Supplier;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SupplierMapper {
    public Supplier toEntity(SupplierCreationDTO supplierCreationDTO);
    @Mapping(source = "products", target = "products")
    public BasicSupplierDTO toBasicDTO(Supplier supplier);
}
