package com.stock.inventario.measurementUnits.services;

import com.stock.inventario.measurementUnits.dto.BasicMeasurementUnitDTO;
import com.stock.inventario.measurementUnits.dto.MeasurementUnitCreationDTO;
import com.stock.inventario.measurementUnits.interfaces.MeasurementUnitService;
import com.stock.inventario.measurementUnits.mappers.MeasurementUnitMapper;
import com.stock.inventario.measurementUnits.models.MeasurementUnit;
import com.stock.inventario.measurementUnits.repositories.MeasurementUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MeasurementUnitServiceImpl implements MeasurementUnitService {

    @Autowired
    private MeasurementUnitRepository measurementUnitRepository;
    @Autowired
    private MeasurementUnitMapper measurementUnitMapper;
    @Override
    public List<BasicMeasurementUnitDTO> getMeasurementUnits() {
        return this.measurementUnitRepository.findAll()
                .stream()
                .map(measurementUnit -> this.measurementUnitMapper.toBasicDTO(measurementUnit)).toList();
    }

    @Override
    public BasicMeasurementUnitDTO getMeasurementUnitById(String id) throws ChangeSetPersister.NotFoundException {
        MeasurementUnit measurementUnit = this.measurementUnitRepository.findById(id).orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        return this.measurementUnitMapper.toBasicDTO(measurementUnit);
    }

    @Override
    public BasicMeasurementUnitDTO createMeasurementUnit(MeasurementUnitCreationDTO measurementUnitCreation) {
        return this.measurementUnitMapper.toBasicDTO(this.measurementUnitRepository.save(this.measurementUnitMapper.toEntity(measurementUnitCreation)));
    }

    @Override
    public BasicMeasurementUnitDTO updateMeasurementUnit(MeasurementUnitCreationDTO measurementUnitCreation, String id) throws ChangeSetPersister.NotFoundException {
        this.getMeasurementUnitById(id);
        MeasurementUnit measurementUnit = this.measurementUnitMapper.toEntity(measurementUnitCreation);
        measurementUnit.setId(id);
        return this.measurementUnitMapper.toBasicDTO(this.measurementUnitRepository.save(measurementUnit));
    }

    @Override
    public void deleteMeasurementUnit(String id) throws ChangeSetPersister.NotFoundException {
        this.getMeasurementUnitById(id);
        this.measurementUnitRepository.deleteById(id);
    }
}
