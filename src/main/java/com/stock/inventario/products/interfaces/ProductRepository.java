package com.stock.inventario.products.interfaces;

import com.stock.inventario.products.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {

}
