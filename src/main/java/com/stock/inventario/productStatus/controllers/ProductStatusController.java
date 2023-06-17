package com.stock.inventario.productStatus.controllers;

import com.stock.inventario.common.ApiResponse;
import com.stock.inventario.productStatus.dto.BasicProductStatusDTO;
import com.stock.inventario.productStatus.dto.ProductStatusCreationDTO;
import com.stock.inventario.productStatus.interfaces.ProductStatusService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products-status")
public class ProductStatusController {
    @Autowired
    private ProductStatusService productStatusService;

    @GetMapping
    public ResponseEntity<ApiResponse> index(){
        try {
            List<BasicProductStatusDTO> productsStatus = this.productStatusService.getProductsStatus();
            return new ResponseEntity(new ApiResponse(true, productsStatus), HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage(), e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> show(@PathVariable String id){
        try {
            BasicProductStatusDTO productStatus = this.productStatusService.getProductStatusById( id );
            return new ResponseEntity(new ApiResponse(true, productStatus), HttpStatus.OK);
        }catch (ChangeSetPersister.NotFoundException e){
            return new ResponseEntity<>(new ApiResponse(false, "Recurso no encontrado"), HttpStatus.NOT_FOUND);
        }catch(Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage(), e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse> store(@Valid @RequestBody ProductStatusCreationDTO productStatusCreationDTO){
        try {
            BasicProductStatusDTO productStatus = this.productStatusService.createProductStatus( productStatusCreationDTO );
            return new ResponseEntity<>(new ApiResponse(true, productStatus), HttpStatus.CREATED);
        }catch(Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage(), e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable String id, @Valid @RequestBody ProductStatusCreationDTO productStatusCreationDTO){
        try {
            BasicProductStatusDTO productStatus = this.productStatusService.updateProductStatus(productStatusCreationDTO, id);
            return new ResponseEntity<>(new ApiResponse(true, productStatus), HttpStatus.OK);
        }catch (ChangeSetPersister.NotFoundException e){
            return new ResponseEntity<>(new ApiResponse(false, "Recurso no encontrado"), HttpStatus.NOT_FOUND);
        }catch(Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage(), e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable String id){
        try {
            this.productStatusService.deleteProductStatus( id );
            return new ResponseEntity<>(new ApiResponse(true, "Estado eliminado con éxito"), HttpStatus.NO_CONTENT);
        }catch (ChangeSetPersister.NotFoundException e){
            return new ResponseEntity<>(new ApiResponse(false, "Recurso no encontrado"), HttpStatus.NOT_FOUND);
        }catch(Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage(), e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
