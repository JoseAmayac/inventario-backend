package com.stock.inventario.permissions.mappers;

import com.stock.inventario.permissions.dto.BasicPermissionDTO;
import com.stock.inventario.permissions.dto.PermissionCreationDTO;
import com.stock.inventario.permissions.models.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    public Permission toEntity(PermissionCreationDTO permissionCreationDTO);
    public BasicPermissionDTO toBasicDTO(Permission permission);
}
