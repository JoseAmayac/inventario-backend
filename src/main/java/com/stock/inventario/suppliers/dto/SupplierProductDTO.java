package com.stock.inventario.suppliers.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class SupplierProductDTO implements Serializable {
    @NotNull(message = "El proveedor no puede ser nulo")
    @NotBlank(message = "El proveedor no puede estar en blanco")
    private String supplierId;
    @NotNull(message = "El producto no puede ser nulo")
    @NotBlank(message = "El producto no puede estar en blanco")
    private String productId;
}
