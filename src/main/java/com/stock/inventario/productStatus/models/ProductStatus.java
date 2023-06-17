package com.stock.inventario.productStatus.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("products_status")
@Getter
@Setter
public class ProductStatus {
    @Id
    private String id;

    private String name;
    private String tag;
}
