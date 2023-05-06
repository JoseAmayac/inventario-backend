package com.stock.inventario.brands.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BrandCreationDTO {
    @NotNull(message = "El nombre es obligatori")
    @NotBlank(message = "El nombre no puede estar vacío")
    private String name;
    private String image;
}
