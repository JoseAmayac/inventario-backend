package com.stock.inventario.permissions.interfaces;

import com.stock.inventario.permissions.dto.BasicPermissionDTO;
import com.stock.inventario.permissions.dto.PermissionCreationDTO;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface PermissionService {
    public List<BasicPermissionDTO> getPermissions();
    public BasicPermissionDTO getPermissionById(String id) throws ChangeSetPersister.NotFoundException;
    public BasicPermissionDTO createPermission(PermissionCreationDTO permissionCreationDTO);
    public BasicPermissionDTO updatePermission(String id, PermissionCreationDTO permissionCreationDTO) throws ChangeSetPersister.NotFoundException;
    public void deletePermission(String id) throws ChangeSetPersister.NotFoundException;
}
