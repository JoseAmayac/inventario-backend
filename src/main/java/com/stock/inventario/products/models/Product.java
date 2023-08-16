package com.stock.inventario.products.models;

import com.stock.inventario.brands.models.Brand;
import com.stock.inventario.categories.models.Category;
import com.stock.inventario.measurementUnits.models.MeasurementUnit;
import com.stock.inventario.productStatus.models.ProductStatus;
import com.stock.inventario.suppliers.models.Supplier;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

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
    private Integer rating;
    private String slug;
    private Integer discount;
    private String code;
    private String photo;


    @DBRef
    private Supplier supplier;

    @DBRef
    private MeasurementUnit unit;

    @DBRef
    private Brand brand;

    @DBRef
    private ProductStatus status;

    @DBRef
    private Category category;
}
