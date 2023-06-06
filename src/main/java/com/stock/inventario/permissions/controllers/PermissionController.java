package com.stock.inventario.permissions.controllers;

import com.stock.inventario.common.ApiResponse;
import com.stock.inventario.permissions.dto.BasicPermissionDTO;
import com.stock.inventario.permissions.dto.PermissionCreationDTO;
import com.stock.inventario.permissions.interfaces.PermissionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permissions")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    @GetMapping
    public ResponseEntity<ApiResponse> index() {
        try {
            List<BasicPermissionDTO> permissions = this.permissionService.getPermissions();
            return new ResponseEntity<>(new ApiResponse(true, permissions), HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage(), e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> show(@PathVariable String id) {
        try {
            BasicPermissionDTO permission = this.permissionService.getPermissionById(id);
            return new ResponseEntity<>(new ApiResponse(true, permission), HttpStatus.OK);
        }catch (ChangeSetPersister.NotFoundException e){
            return new ResponseEntity<>(new ApiResponse(false, "Recurso no encontrado"), HttpStatus.NOT_FOUND);
        }catch(Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage(), e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping
    public ResponseEntity<ApiResponse> store(@Valid @RequestBody PermissionCreationDTO permissionCreationDTO) {
        try {
            BasicPermissionDTO basicPermissionDTO = this.permissionService.createPermission(permissionCreationDTO);
            return new ResponseEntity<>(new ApiResponse(true, basicPermissionDTO), HttpStatus.CREATED);
        }catch(Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage(), e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable String id, @Valid @RequestBody PermissionCreationDTO permissionCreationDTO) {
        try {
            BasicPermissionDTO basicPermissionDTO = this.permissionService.updatePermission(id, permissionCreationDTO);
            return new ResponseEntity<>(new ApiResponse(true, basicPermissionDTO), HttpStatus.OK);
        }catch (ChangeSetPersister.NotFoundException e){
            return new ResponseEntity<>(new ApiResponse(false, "Recurso no encontrado"), HttpStatus.NOT_FOUND);
        }catch(Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage(), e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable String id) {
        try {
            this.permissionService.deletePermission(id);
            return new ResponseEntity<>(new ApiResponse(true, "Permiso eliminado con exito"), HttpStatus.OK);
        }catch (ChangeSetPersister.NotFoundException e){
            return new ResponseEntity<>(new ApiResponse(false, "Recurso no encontrado"), HttpStatus.NOT_FOUND);
        }catch(Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage(), e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
