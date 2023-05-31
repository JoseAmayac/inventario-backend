package com.stock.inventario.roles.interfaces;

import com.stock.inventario.roles.dto.BasicRoleDTO;
import com.stock.inventario.roles.dto.RoleCreationDTO;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface RoleService {
    public List<BasicRoleDTO> getRoles();
    public BasicRoleDTO getRoleById(String id) throws ChangeSetPersister.NotFoundException;
    public BasicRoleDTO createRole(RoleCreationDTO roleCreationDTO);
    public BasicRoleDTO updateRole(String id, RoleCreationDTO roleCreationDTO) throws ChangeSetPersister.NotFoundException;
    public void deleteRole(String id) throws ChangeSetPersister.NotFoundException;
}
