package com.stock.inventario.supplierDocuments.models;

import com.stock.inventario.suppliers.models.Supplier;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
@Getter
@Setter
@Document("supplierDocuments")
public class SupplierDocument {
    @Id
    private String id;
    private String link;

    @DBRef
    private Supplier supplier;
}
