package com.stock.inventario.supplierDocuments.mappers;

import com.stock.inventario.supplierDocuments.dto.BasicSupplierDocumentDTO;
import com.stock.inventario.supplierDocuments.dto.SupplierDocumentCreationDTO;
import com.stock.inventario.supplierDocuments.models.SupplierDocument;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SupplierDocumentMapper {
    SupplierDocument toEntity(SupplierDocumentCreationDTO supplierDocumentCreation);
    @Mapping(source = "supplier", target = "supplier")
    BasicSupplierDocumentDTO toBasicDTO(SupplierDocument supplierDocument);
}
