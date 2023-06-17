package com.stock.inventario.measurementUnits.mappers;

import com.stock.inventario.measurementUnits.dto.BasicMeasurementUnitDTO;
import com.stock.inventario.measurementUnits.dto.MeasurementUnitCreationDTO;
import com.stock.inventario.measurementUnits.models.MeasurementUnit;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MeasurementUnitMapper {
    MeasurementUnit toEntity(MeasurementUnitCreationDTO measurementUnitCreation);
    BasicMeasurementUnitDTO toBasicDTO(MeasurementUnit measurementUnit);
}
