package com.stock.inventario.suppliers.dto;

import com.stock.inventario.products.dto.BasicProductDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BasicSupplierDTO extends SupplierCreationDTO {
    private String id;
    private List<BasicProductDTO> products;
}
