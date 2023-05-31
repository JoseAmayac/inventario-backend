package com.stock.inventario.products.models;

import com.stock.inventario.suppliers.models.Supplier;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Getter
@Setter
@Document("products")
public class Product {
    @Id
    private String id;

    private String name;
    private Double price;
    private Double cost;
    private String description;
    private Integer stock;

    @DBRef
    private Supplier supplier;
}
