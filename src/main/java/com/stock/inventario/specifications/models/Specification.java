package com.stock.inventario.specifications.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document("specifications")
public class Specification {
    @Id
    private String id;
    private String name;
    private String description;
}
