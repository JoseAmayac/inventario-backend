package com.stock.inventario.roles.mappers;

import com.stock.inventario.roles.dto.BasicRoleDTO;
import com.stock.inventario.roles.dto.RoleCreationDTO;
import com.stock.inventario.roles.models.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    public Role toEntity(RoleCreationDTO roleCreationDTO);
    public BasicRoleDTO toBasicDTO(Role role);
}
