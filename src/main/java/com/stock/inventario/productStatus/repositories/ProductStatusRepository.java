package com.stock.inventario.productStatus.repositories;

import com.stock.inventario.productStatus.models.ProductStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductStatusRepository extends MongoRepository<ProductStatus, String> {
}
