package com.stock.inventario.suppliers.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
public class SupplierCreationDTO implements Serializable {
    @NotNull(message = "La razón social es obligatoria")
    @NotBlank(message = "La razón social no puede estar vacía")
    private String companyName;
    @NotNull(message = "El nit es obligatorio")
    @NotBlank(message = "El nit no puede estar vacío")
    private String nit;
    private String address;
    @NotNull(message = "El teléfono es obligatorio")
    private String phone;
    @Email(message = "Formato de correo electrónico no es válido")
    private String email;
    private String companyWebUrl;
}
