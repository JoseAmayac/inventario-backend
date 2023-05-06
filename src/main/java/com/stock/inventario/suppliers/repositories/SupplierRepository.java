package com.stock.inventario.suppliers.repositories;

import com.stock.inventario.suppliers.models.Supplier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends MongoRepository<Supplier, String> {
}
