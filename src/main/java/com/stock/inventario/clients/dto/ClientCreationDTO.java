package com.stock.inventario.clients.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ClientCreationDTO implements Serializable {
    @NotNull(message = "El nombre es obligatorio")
    @NotBlank(message = "El nombre no puede estar vacío")
    private String name;
    @NotNull(message = "El documento es obligatorio")
    @NotBlank(message = "El documento no puede estar vacío")
    private String document;
    private String phone;
    private String email;
}
