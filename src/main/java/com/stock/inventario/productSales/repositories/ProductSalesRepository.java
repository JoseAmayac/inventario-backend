package com.stock.inventario.productSales.repositories;

import com.stock.inventario.productSales.models.ProductSale;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductSalesRepository extends MongoRepository<ProductSale, String> {
}
