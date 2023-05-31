package com.stock.inventario.users.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class UserRoleDTO implements Serializable {
    @NotNull(message = "La lista de roles es obligatoria")
    @NotBlank(message = "La lista de roles no puede estar en blanco")
    private List<String> roleIds;
}
