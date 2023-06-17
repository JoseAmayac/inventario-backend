package com.stock.inventario.measurementUnits.repositories;

import com.stock.inventario.measurementUnits.models.MeasurementUnit;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementUnitRepository extends MongoRepository<MeasurementUnit, String> {
}
