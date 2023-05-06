package com.stock.inventario.brands.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document("brands")
public class Brand {
    @Id
    private String id;
    private String name;
    private String image;
}
