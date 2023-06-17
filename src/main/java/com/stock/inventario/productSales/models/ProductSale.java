package com.stock.inventario.productSales.models;

import com.stock.inventario.products.models.Product;
import com.stock.inventario.sales.models.Sale;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document("products_sales")
public class ProductSale {
    @Id
    private String id;

    private Double value;
    private Double quantity;
    @DBRef
    private Product product;
    @DBRef
    private Sale sale;
}
