package com.stock.inventario.specifications.controllers;

import com.stock.inventario.common.ApiResponse;
import com.stock.inventario.specifications.dto.BasicSpecificationDTO;
import com.stock.inventario.specifications.dto.SpecificationCreationDTO;
import com.stock.inventario.specifications.interfaces.SpecificationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/specifications")
public class SpecificationController {
    @Autowired
    private SpecificationService specificationService;

    @GetMapping
    public ResponseEntity<ApiResponse> index() {
        try {
            List<BasicSpecificationDTO> specifications = this.specificationService.getAllSpecifications();
            return new ResponseEntity<>(new ApiResponse(true, specifications), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage(), e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> show(@PathVariable String id) {
        try {
            BasicSpecificationDTO specification = this.specificationService.getSpecificationById(id);
            return new ResponseEntity<>(new ApiResponse(true, specification), HttpStatus.OK);
        } catch (ChangeSetPersister.NotFoundException notFoundException) {
            return new ResponseEntity<>(new ApiResponse(false, "Recurso no encontrado"), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage(), e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse> store(@Valid @RequestBody SpecificationCreationDTO specificationCreationDTO) {
        try {
            BasicSpecificationDTO speciication = this.specificationService.createSpecification(specificationCreationDTO);
            return new ResponseEntity<>(new ApiResponse(true, speciication), HttpStatus.OK);
        } catch (Exception e) {
            return  new ResponseEntity<>(new ApiResponse(false, e.getMessage(), e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable String id, @Valid @RequestBody SpecificationCreationDTO specificationCreationDTO) {
        try {
            BasicSpecificationDTO specificationUpdated = this.specificationService.updateSpecification(id, specificationCreationDTO);
            return new ResponseEntity<>(new ApiResponse(true, specificationUpdated), HttpStatus.OK);
        } catch (ChangeSetPersister.NotFoundException notFoundException) {
            return new ResponseEntity<>(new ApiResponse(false, "Recurso no encontrado"), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage(), e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable String id) {
        try{
            this.specificationService.deleteSpecification(id);
            return new ResponseEntity<>(new ApiResponse(true, "Especificación eliminada con éxito"), HttpStatus.OK);
        } catch (ChangeSetPersister.NotFoundException notFoundException) {
            return new ResponseEntity<>(new ApiResponse(false, "Recurso no encontrado"), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage(), e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
