package com.stock.inventario.productStatus.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ProductStatusCreationDTO implements Serializable {
    @NotNull(message = "El nombre del estado es obligatorio")
    @NotBlank(message = "El nombre del estado no puede estar vacío")
    private String name;
    @NotNull(message = "La etiqueta del estado es obligatoria")
    @NotBlank(message = "La etiqueta no puede estar vacío")
    private String tag;
}
