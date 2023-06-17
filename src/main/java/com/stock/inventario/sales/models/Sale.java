package com.stock.inventario.sales.models;

import com.stock.inventario.clients.models.Client;
import com.stock.inventario.users.models.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
@Getter
@Setter
@Document("sales")
public class Sale {
    @Id
    private String id;
    private Date date;
    private Double total;
    @DBRef
    private Client client;
    @DBRef
    private User salesman;
}
