package com.stock.inventario.measurementUnits.interfaces;

import com.stock.inventario.measurementUnits.dto.BasicMeasurementUnitDTO;
import com.stock.inventario.measurementUnits.dto.MeasurementUnitCreationDTO;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface MeasurementUnitService {

    public List<BasicMeasurementUnitDTO> getMeasurementUnits();

    public BasicMeasurementUnitDTO getMeasurementUnitById(String id) throws ChangeSetPersister.NotFoundException;

    public BasicMeasurementUnitDTO createMeasurementUnit(MeasurementUnitCreationDTO measurementUnitCreation);

    public BasicMeasurementUnitDTO updateMeasurementUnit(MeasurementUnitCreationDTO measurementUnitCreation, String id) throws ChangeSetPersister.NotFoundException;

    public void deleteMeasurementUnit(String id) throws ChangeSetPersister.NotFoundException;
}
