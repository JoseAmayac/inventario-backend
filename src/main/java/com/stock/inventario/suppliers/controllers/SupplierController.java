package com.stock.inventario.suppliers.controllers;

import com.stock.inventario.common.ApiResponse;
import com.stock.inventario.suppliers.dto.BasicSupplierDTO;
import com.stock.inventario.suppliers.dto.SupplierCreationDTO;
import com.stock.inventario.suppliers.interfaces.SupplierService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/suppliers")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;
    @GetMapping
    public ResponseEntity<ApiResponse> index() {
        try {
            List<BasicSupplierDTO> suppliers = this.supplierService.getAllSuppliers();
            return new ResponseEntity<>(new ApiResponse(true, suppliers), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage(), e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> show(@PathVariable String id) {
        try {
            BasicSupplierDTO supplier = this.supplierService.getSupplierById(id);
            return new ResponseEntity<>(new ApiResponse(true, supplier), HttpStatus.OK);
        }catch(ChangeSetPersister.NotFoundException notFoundException) {
            return new ResponseEntity<>(new ApiResponse(false, "Recurso no encontrado"), HttpStatus.NOT_FOUND);
        }catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage(), e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping
    public ResponseEntity<ApiResponse> store(@Valid @RequestBody SupplierCreationDTO supplierCreationDTO) {
        try {
            BasicSupplierDTO basicSupplierDTO = this.supplierService.createSupplier(supplierCreationDTO);
            return new ResponseEntity<>(new ApiResponse(true, basicSupplierDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage(), e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable String id, @Valid @RequestBody SupplierCreationDTO supplierCreationDTO) {
        try {
            BasicSupplierDTO basicSupplierDTO = this.supplierService.updateSupplier(id, supplierCreationDTO);
            return new ResponseEntity<>(new ApiResponse(true, basicSupplierDTO), HttpStatus.OK);
        } catch (ChangeSetPersister.NotFoundException notFoundException) {
            return new ResponseEntity<>(new ApiResponse(false, "Recurso no encontrado"), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage(), e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable String id) {
        try {
            this.supplierService.deleteSupplier(id);
            return new ResponseEntity<>(new ApiResponse(true, "Proveedor eliminado con éxito"), HttpStatus.OK);
        } catch (ChangeSetPersister.NotFoundException notFoundException) {
            return new ResponseEntity<>(new ApiResponse(false, "Recurso no encontrado"), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage(), e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
