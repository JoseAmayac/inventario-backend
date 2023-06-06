package com.stock.inventario.permissions.dto;

import com.stock.inventario.permissions.validations.NameValidation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PermissionCreationDTO implements Serializable {
    @NotNull(message = "El nombre del permiso es obligatorio")
    @NotBlank(message = "El nombre del permiso no puede estar vacío")
    @NameValidation
    private String name;
}
