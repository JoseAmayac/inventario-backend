package com.stock.inventario.measurementUnits.controllers;

import com.stock.inventario.common.ApiResponse;
import com.stock.inventario.measurementUnits.dto.BasicMeasurementUnitDTO;
import com.stock.inventario.measurementUnits.dto.MeasurementUnitCreationDTO;
import com.stock.inventario.measurementUnits.interfaces.MeasurementUnitService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/measurementUnits")
public class MeasurementUnitController {

    @Autowired
    private MeasurementUnitService measurementUnitService;

    @GetMapping
    public ResponseEntity<ApiResponse> index() {
        try {
            List<BasicMeasurementUnitDTO> measurementUnits = this.measurementUnitService.getMeasurementUnits();
            return new ResponseEntity<>(new ApiResponse(true, measurementUnits), HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage(), e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public  ResponseEntity<ApiResponse> show(@PathVariable String id) {
        try {
            BasicMeasurementUnitDTO measurementUnit = this.measurementUnitService.getMeasurementUnitById(id);
            return new ResponseEntity<>(new ApiResponse(true, measurementUnit), HttpStatus.OK);
        }catch (ChangeSetPersister.NotFoundException e){
            return new ResponseEntity<>(new ApiResponse(false, "Recurso no encontrado"), HttpStatus.NOT_FOUND);
        }catch(Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage(), e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse> store(@Valid @RequestBody MeasurementUnitCreationDTO measurementUnitCreation) {
        try {
            BasicMeasurementUnitDTO measurementUnit = this.measurementUnitService.createMeasurementUnit(measurementUnitCreation);
            return new ResponseEntity<>(new ApiResponse(true, measurementUnit), HttpStatus.CREATED);
        }catch(Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage(), e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable String id, @Valid @RequestBody MeasurementUnitCreationDTO measurementUnitCreation) {
        try {
            BasicMeasurementUnitDTO measurementUnit = this.measurementUnitService.updateMeasurementUnit(measurementUnitCreation, id);
            return new ResponseEntity<>(new ApiResponse(true, measurementUnit), HttpStatus.OK);
        }catch (ChangeSetPersister.NotFoundException e){
            return new ResponseEntity<>(new ApiResponse(false, "Recurso no encontrado"), HttpStatus.NOT_FOUND);
        }catch(Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage(), e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable String id) {
        try {
            this.measurementUnitService.deleteMeasurementUnit(id);
            return new ResponseEntity<>(new ApiResponse(true, "Unidad de medida eliminada con exito"), HttpStatus.OK);
        }catch (ChangeSetPersister.NotFoundException e){
            return new ResponseEntity<>(new ApiResponse(false, "Recurso no encontrado"), HttpStatus.NOT_FOUND);
        }catch(Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage(), e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
