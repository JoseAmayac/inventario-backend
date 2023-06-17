package com.stock.inventario.roles.controllers;

import com.stock.inventario.common.ApiResponse;
import com.stock.inventario.roles.dto.BasicRoleDTO;
import com.stock.inventario.roles.dto.RoleCreationDTO;
import com.stock.inventario.roles.dto.RolePermissionDTO;
import com.stock.inventario.roles.interfaces.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    public ResponseEntity<ApiResponse> index(){
        try{
            List<BasicRoleDTO> roles = this.roleService.getRoles();
            return new ResponseEntity<>(new ApiResponse(true, roles), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage(), e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> show(@PathVariable String id){
        try{
            BasicRoleDTO role = this.roleService.getRoleById( id );
            return new ResponseEntity<>(new ApiResponse(true, role), HttpStatus.OK);
        }catch (ChangeSetPersister.NotFoundException e){
            return new ResponseEntity<>(new ApiResponse(false, "Recurso no encontrado"), HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage(), e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse> store(@RequestBody RoleCreationDTO roleCreationDTO){
        try{
            BasicRoleDTO role = this.roleService.createRole( roleCreationDTO );
            return new ResponseEntity<>(new ApiResponse(true, role), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage(), e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable String id, @RequestBody RoleCreationDTO roleCreationDTO){
        try {
            BasicRoleDTO role = this.roleService.updateRole( id, roleCreationDTO );
            return new ResponseEntity<>(new ApiResponse(true, role), HttpStatus.OK);
        }catch (ChangeSetPersister.NotFoundException e){
            return new ResponseEntity<>(new ApiResponse(false, "Recurso no encontrado"), HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage(), e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable String id){
        try {
            this.roleService.deleteRole( id );
            return new ResponseEntity<>(new ApiResponse(true, "Rol eliminado correctamente"), HttpStatus.NO_CONTENT);
        }catch (ChangeSetPersister.NotFoundException e){
            return new ResponseEntity<>(new ApiResponse(false, "Recurso no encontrado"), HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage(), e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/permissions/update")
    public ResponseEntity<ApiResponse> syncPermissions(@RequestBody RolePermissionDTO rolePermissionDTO){
        try{
            this.roleService.syncPermissions(rolePermissionDTO.getRoleId(), rolePermissionDTO.getPermissionsIds());
            return new ResponseEntity<>(new ApiResponse(true, "Permisos sincronizados correctamente"), HttpStatus.OK);
        }catch (ChangeSetPersister.NotFoundException e){
            return new ResponseEntity<>(new ApiResponse(false, "Recurso no encontrado"), HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage(), e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
