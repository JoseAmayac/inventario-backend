package com.stock.inventario.auth.controllers;

import com.stock.inventario.auth.dto.AuthenticationResponseDTO;
import com.stock.inventario.auth.dto.CredentialsDTO;
import com.stock.inventario.auth.services.AuthService;
import com.stock.inventario.common.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@Valid @RequestBody CredentialsDTO credentials){
        try{
            AuthenticationResponseDTO response = this.authService.login(credentials);
            return new ResponseEntity<>(new ApiResponse(true, response), HttpStatus.OK);
        }catch (BadCredentialsException ex){
            return new ResponseEntity<>(new ApiResponse(false, ex.getMessage()), HttpStatus.UNAUTHORIZED);
        }catch (Exception ex){
            return new ResponseEntity<>(new ApiResponse(false, ex.getMessage(), ex), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
