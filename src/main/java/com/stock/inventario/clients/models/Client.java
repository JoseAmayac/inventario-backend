package com.stock.inventario.clients.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document("clients")
public class Client {
    @Id
    private String id;
    private String name;
    private String document;
    private String phone;
    private String email;
}
