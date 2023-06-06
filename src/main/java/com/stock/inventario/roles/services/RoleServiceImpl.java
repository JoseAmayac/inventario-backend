package com.stock.inventario.roles.services;

import com.stock.inventario.permissions.models.Permission;
import com.stock.inventario.permissions.repositories.PermissionRepository;
import com.stock.inventario.roles.dto.BasicRoleDTO;
import com.stock.inventario.roles.dto.RoleCreationDTO;
import com.stock.inventario.roles.interfaces.RoleService;
import com.stock.inventario.roles.mappers.RoleMapper;
import com.stock.inventario.roles.models.Role;
import com.stock.inventario.roles.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<BasicRoleDTO> getRoles() {
        return this.roleRepository.findAll().stream().map((role -> this.roleMapper.toBasicDTO( role ))).toList();
    }

    @Override
    public BasicRoleDTO getRoleById(String id) throws ChangeSetPersister.NotFoundException {
        Role role = this.roleRepository.findById( id ).orElseThrow(()-> new ChangeSetPersister.NotFoundException());
        return this.roleMapper.toBasicDTO( role );
    }

    @Override
    public BasicRoleDTO createRole(RoleCreationDTO roleCreationDTO) {
        return this.roleMapper.toBasicDTO( this.roleRepository.save( this.roleMapper.toEntity( roleCreationDTO )) );
    }

    @Override
    public BasicRoleDTO updateRole(String id, RoleCreationDTO roleCreationDTO) throws ChangeSetPersister.NotFoundException {
        this.getRoleById( id );
        Role role = this.roleMapper.toEntity( roleCreationDTO );
        role.setId( id );
        return this.roleMapper.toBasicDTO( this.roleRepository.save( role ));
    }

    @Override
    public void deleteRole(String id) throws ChangeSetPersister.NotFoundException {
        this.getRoleById( id );
        this.roleRepository.deleteById( id );
    }

    @Override
    public void syncPermissions(String roleId, List<String> permissionsIds) throws ChangeSetPersister.NotFoundException {
        Role role = this.roleRepository.findById( roleId ).orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        role.setPermissions(new ArrayList<>());
        this.roleRepository.save( role );

        for (String permissionId: permissionsIds){
            Permission permission = this.permissionRepository.findById( permissionId ).orElseThrow(() -> new ChangeSetPersister.NotFoundException());
            role.getPermissions().add( permission );
        }

        this.roleRepository.save( role );
    }

}
