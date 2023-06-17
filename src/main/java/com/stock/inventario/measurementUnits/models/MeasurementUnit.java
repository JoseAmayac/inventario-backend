package com.stock.inventario.measurementUnits.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Getter
@Setter
@Document("measurementUnits")
public class MeasurementUnit {
    @Id
    private String id;
    private String name;
    private String acronym;
}
