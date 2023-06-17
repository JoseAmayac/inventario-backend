package com.stock.inventario.productSales.controllers;

import com.stock.inventario.common.ApiResponse;
import com.stock.inventario.productSales.dto.BasicProductSaleDTO;
import com.stock.inventario.productSales.dto.ProductSaleCreationDTO;
import com.stock.inventario.productSales.interfaces.ProductSaleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products-sales")
public class ProductSaleController {
    @Autowired
    private ProductSaleService productSaleService;

    @GetMapping("/product/{productId}")
    public ResponseEntity<ApiResponse> getProductSales(@PathVariable String productId){
        try {
            List<BasicProductSaleDTO> productSales = this.productSaleService.getProductSales( productId );
            return new ResponseEntity<>(new ApiResponse(true, productSales), HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(new ApiResponse(false, ex.getMessage(), ex), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/sale/{saleId}")
    public ResponseEntity<ApiResponse> getSaleProducts(@PathVariable String saleId){
        try {
            List<BasicProductSaleDTO> saleProducts = this.productSaleService.getSaleProducts( saleId );
            return new ResponseEntity<>(new ApiResponse(true, saleProducts), HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(new ApiResponse(false, ex.getMessage(), ex), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> show(@PathVariable String id){
        try {
            BasicProductSaleDTO productSale = this.productSaleService.getProductSaleById( id );
            return new ResponseEntity<>(new ApiResponse(true, productSale), HttpStatus.OK);
        }catch (ChangeSetPersister.NotFoundException ex){
            return new ResponseEntity<>(new ApiResponse(false, "Recurso no encontrado"), HttpStatus.NOT_FOUND);
        }catch (Exception ex){
            return new ResponseEntity<>(new ApiResponse(false, ex.getMessage(), ex), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse> store(@Valid @RequestBody ProductSaleCreationDTO productSaleCreationDTO){
        try {
            BasicProductSaleDTO productSale = this.productSaleService.createProductSale( productSaleCreationDTO );
            return new ResponseEntity<>(new ApiResponse(true, productSale), HttpStatus.CREATED);
        }catch (Exception ex){
            return new ResponseEntity<>(new ApiResponse(false, ex.getMessage(), ex), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable String id, @Valid @RequestBody ProductSaleCreationDTO productSaleCreationDTO){
        try {
            BasicProductSaleDTO productSale = this.productSaleService.updateProductSale(productSaleCreationDTO, id);
            return new ResponseEntity<>(new ApiResponse(true, productSale), HttpStatus.OK);
        }catch (ChangeSetPersister.NotFoundException ex){
            return new ResponseEntity<>(new ApiResponse(false, "Recurso no encontrado"), HttpStatus.NOT_FOUND);
        }catch (Exception ex){
            return new ResponseEntity<>(new ApiResponse(false, ex.getMessage(), ex), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable String id){
        try{
            this.productSaleService.deleteProductSale( id );
            return new ResponseEntity<>(new ApiResponse(true, "Producto eliminado de la venta con éxito"), HttpStatus.NO_CONTENT);
        }catch (ChangeSetPersister.NotFoundException ex){
            return new ResponseEntity<>(new ApiResponse(false, "Recurso no encontrado"), HttpStatus.NOT_FOUND);
        }catch (Exception ex){
            return new ResponseEntity<>(new ApiResponse(false, ex.getMessage(), ex), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
