package com.stock.inventario.sales.controllers;

import com.stock.inventario.common.ApiResponse;
import com.stock.inventario.sales.dto.BasicSaleDTO;
import com.stock.inventario.sales.dto.SaleCreationDTO;
import com.stock.inventario.sales.interfaces.SaleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sales")
public class SaleController {
    @Autowired
    private SaleService saleService;

    @GetMapping
    public ResponseEntity<ApiResponse> index() {
        try {
            List<BasicSaleDTO> sales = this.saleService.getSales();
            return new ResponseEntity<>(new ApiResponse(true, sales), HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage(), e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> show(@PathVariable String id) {
        try {
            BasicSaleDTO sale = this.saleService.getSaleById(id);
            return new ResponseEntity<>(new ApiResponse(true, sale), HttpStatus.OK);
        }catch (ChangeSetPersister.NotFoundException e){
            return new ResponseEntity<>(new ApiResponse(false, "Recurso no encontrado"), HttpStatus.NOT_FOUND);
        }catch(Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage(), e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse> store(@Valid @RequestBody SaleCreationDTO saleCreation) {
        try {
            BasicSaleDTO sale = this.saleService.createSale(saleCreation);
            return new ResponseEntity<>(new ApiResponse(true, sale), HttpStatus.CREATED);
        }catch (ChangeSetPersister.NotFoundException e){
            return new ResponseEntity<>(new ApiResponse(false, "Recurso no encontrado"), HttpStatus.NOT_FOUND);
        }catch(Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage(), e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable String id, @Valid @RequestBody SaleCreationDTO saleCreation) {
        try {
            BasicSaleDTO sale = this.saleService.updateSale(id, saleCreation);
            return new ResponseEntity<>(new ApiResponse(true, sale), HttpStatus.OK);
        }catch (ChangeSetPersister.NotFoundException e){
            return new ResponseEntity<>(new ApiResponse(false, "Recurso no encontrado"), HttpStatus.NOT_FOUND);
        }catch(Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage(), e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable String id) {
        try {
            this.saleService.deleteSale(id);
            return new ResponseEntity<>(new ApiResponse(true, "Venta eliminada con exito"), HttpStatus.OK);
        }catch (ChangeSetPersister.NotFoundException e){
            return new ResponseEntity<>(new ApiResponse(false, "Recurso no encontrado"), HttpStatus.NOT_FOUND);
        }catch(Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage(), e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
