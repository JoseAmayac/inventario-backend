package com.stock.inventario.supplierDocuments.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
public class SupplierDocumentCreationDTO implements Serializable {
    @NotNull(message = "La enlace del producto es requerido")
    @NotBlank(message = "El enlace no puede estar vacío")
    private String link;
    @NotNull(message = "El proveedor del producto es requerido")
    @NotBlank(message = "El proveedor no puede estar vacío")
    private  String supplierId;
}
