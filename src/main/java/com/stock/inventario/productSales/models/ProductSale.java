package com.stock.inventario.productSales.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document("products_sales")
public class ProductSale {
    @Id
    private String id;
}
