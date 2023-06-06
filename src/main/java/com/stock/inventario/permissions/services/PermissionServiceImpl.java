package com.stock.inventario.permissions.services;

import com.stock.inventario.permissions.dto.BasicPermissionDTO;
import com.stock.inventario.permissions.dto.PermissionCreationDTO;
import com.stock.inventario.permissions.interfaces.PermissionService;
import com.stock.inventario.permissions.mappers.PermissionMapper;
import com.stock.inventario.permissions.models.Permission;
import com.stock.inventario.permissions.repositories.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private PermissionMapper permissionMapper;
    @Override
    public List<BasicPermissionDTO> getPermissions() {
        return this.permissionRepository.findAll().stream().map((permission -> this.permissionMapper.toBasicDTO(permission))).toList();
    }

    @Override
    public BasicPermissionDTO getPermissionById(String id) throws ChangeSetPersister.NotFoundException {
        Permission permission = this.permissionRepository.findById(id).orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        return this.permissionMapper.toBasicDTO(permission);
    }

    @Override
    public BasicPermissionDTO createPermission(PermissionCreationDTO permissionCreationDTO) {
        return this.permissionMapper.toBasicDTO(this.permissionRepository.save(this.permissionMapper.toEntity(permissionCreationDTO)));
    }

    @Override
    public BasicPermissionDTO updatePermission(String id, PermissionCreationDTO permissionCreationDTO) throws ChangeSetPersister.NotFoundException {
        this.getPermissionById(id);
        Permission permission = this.permissionMapper.toEntity(permissionCreationDTO);
        permission.setId(id);
        return this.permissionMapper.toBasicDTO(this.permissionRepository.save(permission));
    }

    @Override
    public void deletePermission(String id) throws ChangeSetPersister.NotFoundException {
        this.getPermissionById(id);
        this.permissionRepository.deleteById(id);
    }
}
