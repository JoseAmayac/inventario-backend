package com.stock.inventario.auth.dto;

import com.stock.inventario.users.dto.BasicUserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class AuthenticationResponseDTO implements Serializable {
    private String token;
    private BasicUserDTO user;
}
