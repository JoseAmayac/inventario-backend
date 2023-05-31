package com.stock.inventario.products.dto;



import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ProductCreationDTO implements Serializable {

    @NotNull(message = "El nombre es requerido")
    private String name;
    @Min(value = 0, message = "El precio debe ser mayor o igual a cero")
    @NotNull(message = "El precio es requerido")
    private Double price;
    @Min(value = 0, message = "El costo debe ser mayor o igual a cero")
    @NotNull(message = "El costo es requerido")
    private Double cost;
    private String description;
    @Min(value = 0, message = "El stock debe ser mayor o igual a cero")
    @NotNull(message = "El stock es requerido")
    private Integer stock;
    @NotNull(message = "El proveedor del producto es requerido")
    @NotBlank(message = "El proveedor no puede estar vacío")
    private  String supplierId;
}
