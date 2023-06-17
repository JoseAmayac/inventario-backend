package com.stock.inventario.supplierDocuments.dto;

import com.stock.inventario.suppliers.dto.BasicSupplierDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasicSupplierDocumentDTO extends SupplierDocumentCreationDTO {
    private String id;
    private BasicSupplierDTO supplier;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
}
