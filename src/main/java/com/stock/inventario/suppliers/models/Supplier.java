package com.stock.inventario.suppliers.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
}
