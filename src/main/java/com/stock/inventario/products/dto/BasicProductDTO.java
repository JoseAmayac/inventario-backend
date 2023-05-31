package com.stock.inventario.products.dto;

import com.stock.inventario.suppliers.dto.BasicSupplierDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasicProductDTO extends ProductCreationDTO{
    private String id;

    private BasicSupplierDTO supplier;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
}
