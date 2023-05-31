package com.stock.inventario.roles.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class RoleCreationDTO implements Serializable {
    @NotNull(message = "El nombre del rol es obligatorio")
    @NotBlank(message = "El nombre del rol no puede estar vacío")
    private String name;
}
