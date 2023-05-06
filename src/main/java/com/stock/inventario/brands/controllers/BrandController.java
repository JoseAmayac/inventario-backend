package com.stock.inventario.brands.controllers;

import com.stock.inventario.brands.dto.BasicBrandDTO;
import com.stock.inventario.brands.dto.BrandCreationDTO;
import com.stock.inventario.brands.interfaces.BrandService;
import com.stock.inventario.common.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brands")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping
    public ResponseEntity<ApiResponse> index() {
        try {
            List<BasicBrandDTO> brands = this.brandService.getAllBrands();
            return new ResponseEntity<>(new ApiResponse(true, brands), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage(), e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> show(@PathVariable String id) {
        try {
            BasicBrandDTO brand = this.brandService.getBrandById(id);
            return new ResponseEntity<>(new ApiResponse(true, brand), HttpStatus.OK);
        } catch (ChangeSetPersister.NotFoundException notFoundException) {
            return new ResponseEntity<>(new ApiResponse(false, "Recurso no encontrado"), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage(), e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse> store(@Valid @RequestBody BrandCreationDTO brandCreationDTO) {
        try {
            BasicBrandDTO brand = this.brandService.createBrand(brandCreationDTO);
            return new ResponseEntity<>(new ApiResponse(true, brand), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage(), e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable String id, @Valid @RequestBody BrandCreationDTO brandCreationDTO) {
        try {
            BasicBrandDTO brand = this.brandService.updateBrand(id, brandCreationDTO);
            return new ResponseEntity<>(new ApiResponse(true, brand), HttpStatus.OK);
        } catch (ChangeSetPersister.NotFoundException notFoundException) {
            return new ResponseEntity<>(new ApiResponse(false, "Recurso no encontrado"), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage(), e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable String id) {
        try {
            this.brandService.deleteBrand(id);
            return new ResponseEntity<>(new ApiResponse(true, "Marca eliminada con éxito"), HttpStatus.OK);
        } catch (ChangeSetPersister.NotFoundException notFoundException) {
            return new ResponseEntity<>(new ApiResponse(false, "Recurso no encontrado"), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage(), e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
