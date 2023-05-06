package com.stock.inventario.specifications.repositories;

import com.stock.inventario.specifications.models.Specification;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecificationRepository extends MongoRepository<Specification, String> {
}
