package com.stock.inventario.suppliers.models;

import com.stock.inventario.products.models.Product;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Getter
@Setter
@Document("suppliers")
public class Supplier {
    @Id
    private String id;
    private String companyName;
    private String nit;
    private String address;
    private String phone;
    private String email;
    private String companyWebUrl;

    @DocumentReference()
    private List<Product> products;
}
