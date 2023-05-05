package com.stock.inventario.users.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document("users")
public class User {
    @Id
    private String id;
    private String name;
    private String lastName;
    //private String document;
    private String phone;
    private String email;
    private String password;
    private String username;
    private String photoUrl;
}
