package com.stock.inventario.clients.controllers;

import com.stock.inventario.clients.dto.BasicClientDTO;
import com.stock.inventario.clients.dto.ClientCreationDTO;
import com.stock.inventario.clients.interfaces.ClientService;
import com.stock.inventario.common.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping
    public ResponseEntity<ApiResponse> index(){
        try{
            List<BasicClientDTO> clients = this.clientService.getClients();
            return new ResponseEntity<>(new ApiResponse(true, clients), HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(new ApiResponse(false, ex.getMessage(), ex), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> show(@PathVariable String id){
        try {
            BasicClientDTO client = this.clientService.getClientById(id);
            return new ResponseEntity<>(new ApiResponse(true, client), HttpStatus.OK);
        }catch (ChangeSetPersister.NotFoundException ex){
            return new ResponseEntity<>(new ApiResponse(false, "Recurso no encontrado"), HttpStatus.NOT_FOUND);
        }catch (Exception ex){
            return new ResponseEntity<>(new ApiResponse(false, ex.getMessage(), ex), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse> store(@Valid @RequestBody ClientCreationDTO clientCreationDTO){
        try {
            BasicClientDTO client = this.clientService.createClient(clientCreationDTO);
            return new ResponseEntity<>(new ApiResponse(true, client), HttpStatus.CREATED);
        }catch (Exception ex){
            return new ResponseEntity<>(new ApiResponse(false, ex.getMessage(), ex), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable String id, @Valid @RequestBody ClientCreationDTO clientCreationDTO){
        try{
            BasicClientDTO client = this.clientService.updateClient(id, clientCreationDTO);
            return new ResponseEntity<>(new ApiResponse(true, client), HttpStatus.OK);
        }catch (ChangeSetPersister.NotFoundException ex){
            return new ResponseEntity<>(new ApiResponse(false, "Recurso no encontrado"), HttpStatus.NOT_FOUND);
        }catch (Exception ex){
            return new ResponseEntity<>(new ApiResponse(false, ex.getMessage(), ex), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable String id){
        try {
            this.clientService.deleteClient(id);
            return new ResponseEntity<>(new ApiResponse(true, "Cliente eliminado con éxito"), HttpStatus.OK);
        }catch (ChangeSetPersister.NotFoundException ex){
            return new ResponseEntity<>(new ApiResponse(false, "Recurso no encontrado"), HttpStatus.NOT_FOUND);
        }catch (Exception ex){
            return new ResponseEntity<>(new ApiResponse(false, ex.getMessage(), ex), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
