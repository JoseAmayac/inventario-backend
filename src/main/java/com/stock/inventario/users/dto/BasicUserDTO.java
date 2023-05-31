package com.stock.inventario.users.dto;

import com.stock.inventario.roles.models.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BasicUserDTO  extends  UserCreationDTO{
    private String id;

    private List<Role> roles;
}
