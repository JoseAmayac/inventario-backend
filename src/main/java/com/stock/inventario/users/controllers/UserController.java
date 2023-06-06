package com.stock.inventario.users.controllers;

import com.stock.inventario.common.ApiResponse;
import com.stock.inventario.users.dto.BasicUserDTO;
import com.stock.inventario.users.dto.UserCreationDTO;
import com.stock.inventario.users.dto.UserRoleDTO;
import com.stock.inventario.users.interfaces.UserService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<ApiResponse> index(){
        try {
            List<BasicUserDTO> users = this.userService.getUsers();
            return new ResponseEntity<ApiResponse>(new ApiResponse(true, users), HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, ex.getMessage(), ex), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<ApiResponse> show(@PathVariable String id){
        try{
            BasicUserDTO user = this.userService.getUserById(id);
            return new ResponseEntity<>(new ApiResponse(true, user), HttpStatus.OK);
        }catch (ChangeSetPersister.NotFoundException ex){
            return new ResponseEntity<>(new ApiResponse(false, "Recurso no encontrado"), HttpStatus.NOT_FOUND);
        }catch (Exception ex){
            return new ResponseEntity<>(new ApiResponse(false, ex.getMessage(), ex), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse> store(@Valid @RequestBody UserCreationDTO userCreationDTO){
        try{
            BasicUserDTO user = this.userService.createUser(userCreationDTO);
            return new ResponseEntity<>(new ApiResponse(true, user), HttpStatus.CREATED);
        }catch (Exception ex){
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, ex.getMessage(), ex), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@Valid @PathVariable String id, @Valid @RequestBody UserCreationDTO userCreationDTO){
        try{
            BasicUserDTO user = this.userService.updateUser(id, userCreationDTO);
            return new ResponseEntity<>(new ApiResponse(true, user), HttpStatus.OK);
        }catch (ChangeSetPersister.NotFoundException ex){
            return new ResponseEntity<>(new ApiResponse(false, "Recurso no encontrado"), HttpStatus.NOT_FOUND);
        }catch (Exception ex){
            return new ResponseEntity<>(new ApiResponse(false, ex.getMessage(), ex), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable String id){
        try{
            this.userService.deleteUser(id);
            return new ResponseEntity<>(new ApiResponse(true, "Usuario eliminado con éxito"), HttpStatus.OK);
        }catch (ChangeSetPersister.NotFoundException ex){
            return new ResponseEntity<>(new ApiResponse(false, "Recurso no encontrado"), HttpStatus.NOT_FOUND);
        }catch (Exception ex){
            return new ResponseEntity<>(new ApiResponse(false, ex.getMessage(), ex), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}/update-roles")
    public ResponseEntity<ApiResponse> syncRoles(@PathVariable String id, @RequestBody UserRoleDTO userRoleDTO){
        try {
            this.userService.syncUserRoles(id, userRoleDTO.getRoleIds() );
            return new ResponseEntity<>(new ApiResponse(true, "Roles sincronizados correctamente"), HttpStatus.OK);
        }catch (ChangeSetPersister.NotFoundException ex){
            return new ResponseEntity<>(new ApiResponse(false, "Recurso no encontrado"), HttpStatus.NOT_FOUND);
        }catch (Exception ex){
            return new ResponseEntity<>(new ApiResponse(false, ex.getMessage(), ex), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
