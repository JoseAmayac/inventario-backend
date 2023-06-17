package com.stock.inventario.supplierDocuments.controllers;

import com.stock.inventario.common.ApiResponse;
import com.stock.inventario.supplierDocuments.dto.BasicSupplierDocumentDTO;
import com.stock.inventario.supplierDocuments.dto.SupplierDocumentCreationDTO;
import com.stock.inventario.supplierDocuments.interfaces.SupplierDocumentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/supplierDocuments")
public class SupplierDocumentController {
    @Autowired
    private SupplierDocumentService supplierDocumentService;

    @GetMapping
    public ResponseEntity<ApiResponse> index() {
        try {
            List<BasicSupplierDocumentDTO> supplierDocuments = this.supplierDocumentService.getSupplierDocuments();
            return new ResponseEntity<>(new ApiResponse(true, supplierDocuments), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage(), e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> show(@PathVariable String id) {
        try {
            BasicSupplierDocumentDTO supplierDocument = this.supplierDocumentService.getSupplierDocumentById(id);
            return new ResponseEntity<>(new ApiResponse(true, supplierDocument), HttpStatus.OK);
        }catch (ChangeSetPersister.NotFoundException e){
            return new ResponseEntity<>(new ApiResponse(false, "Recurso no encontrado"), HttpStatus.NOT_FOUND);
        }catch(Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage(), e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse> store(@Valid @RequestBody SupplierDocumentCreationDTO supplierDocumentCreation) {
        try {
            BasicSupplierDocumentDTO supplierDocument = this.supplierDocumentService.createSupplierDocument(supplierDocumentCreation);
            return new ResponseEntity<>(new ApiResponse(true, supplierDocument), HttpStatus.CREATED);
        }catch (ChangeSetPersister.NotFoundException e){
            return new ResponseEntity<>(new ApiResponse(false, "Recurso no encontrado"), HttpStatus.NOT_FOUND);
        }catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage(), e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable String id, @Valid @RequestBody SupplierDocumentCreationDTO supplierDocumentCreation) {
        try {
            BasicSupplierDocumentDTO supplierDocument = this.supplierDocumentService.updateSupplierDocument(supplierDocumentCreation, id);
            return new ResponseEntity<>(new ApiResponse(true, supplierDocument), HttpStatus.OK);
        }catch (ChangeSetPersister.NotFoundException e){
            return new ResponseEntity<>(new ApiResponse(false, "Recurso no encontrado"), HttpStatus.NOT_FOUND);
        }catch(Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage(), e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable String id) {
        try {
            this.supplierDocumentService.deleteSupplierDocument(id);
            return new ResponseEntity<>(new ApiResponse(true, "Documento eliminado con exito"), HttpStatus.OK);
        }catch (ChangeSetPersister.NotFoundException e){
            return new ResponseEntity<>(new ApiResponse(false, "Recurso no encontrado"), HttpStatus.NOT_FOUND);
        }catch(Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage(), e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
