package com.stock.inventario.roles.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class RolePermissionDTO implements Serializable {
    private String roleId;
    private List<String> permissionsIds;
}
