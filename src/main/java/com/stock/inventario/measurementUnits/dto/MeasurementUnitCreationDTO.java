package com.stock.inventario.measurementUnits.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
public class MeasurementUnitCreationDTO implements Serializable {
    @NotNull(message = "El nombre es requerido")
    @NotBlank(message = "El nombre no puede estar vacío")
    private String name;
    @NotNull(message = "El acronimo es requerido")
    private String acronym;
}
