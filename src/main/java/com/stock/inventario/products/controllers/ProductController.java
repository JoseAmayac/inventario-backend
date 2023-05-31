package com.stock.inventario.products.controllers;


import com.stock.inventario.common.ApiResponse;
import com.stock.inventario.products.dto.BasicProductDTO;
import com.stock.inventario.products.dto.ProductCreationDTO;
import com.stock.inventario.products.interfaces.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("")
    public ResponseEntity<ApiResponse> index(){
        try {
            List<BasicProductDTO> products = this.productService.getProducts();
            return new ResponseEntity<ApiResponse>(new ApiResponse(true, products), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, e.getMessage(), e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> show(@PathVariable String id){
        try{
            BasicProductDTO  product = this.productService.getProductById(id);

            if (product == null){
                return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Recurso no encontrado"), HttpStatus.NOT_FOUND);
            }

            return ResponseEntity.ok(new ApiResponse(true, product));
        }catch (Exception e){
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, e.getMessage(), e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity<ApiResponse> store(@Valid @RequestBody ProductCreationDTO productDTO){
        try{
            BasicProductDTO productDB = this.productService.createProduct(productDTO);
            return new ResponseEntity<ApiResponse>(new ApiResponse(true, productDB), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, e.getMessage(), e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable String id, @RequestBody ProductCreationDTO productCreationDTO){
        try{
            BasicProductDTO productDB = this.productService.updateProduct(productCreationDTO, id);
            return ResponseEntity.ok(new ApiResponse(true, productDB));
        }catch (ChangeSetPersister.NotFoundException e){
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Recurso no encontrado"), HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, e.getMessage(), e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable String id){
        try {
            this.productService.deleteProduct(id);
            return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Producto eliminado con éxito"), HttpStatus.OK);
        }catch (ChangeSetPersister.NotFoundException e){
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Recurso no encontrado"), HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, e.getMessage(), e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
