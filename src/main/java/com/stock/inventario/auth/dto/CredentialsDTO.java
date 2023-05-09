package com.stock.inventario.auth.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CredentialsDTO implements Serializable {
    private String username;
    private String password;
}
