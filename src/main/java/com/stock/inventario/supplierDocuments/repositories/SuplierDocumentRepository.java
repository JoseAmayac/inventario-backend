package com.stock.inventario.supplierDocuments.repositories;

import com.stock.inventario.supplierDocuments.models.SupplierDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuplierDocumentRepository extends MongoRepository<SupplierDocument, String> {
}
