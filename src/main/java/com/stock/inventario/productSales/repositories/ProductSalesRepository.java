package com.stock.inventario.productSales.repositories;

import com.stock.inventario.productSales.models.ProductSale;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductSalesRepository extends MongoRepository<ProductSale, String> {
    List<ProductSale> findByProduct(String productId);
    List<ProductSale> findBySale(String saleId);
}
