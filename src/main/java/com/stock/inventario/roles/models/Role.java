package com.stock.inventario.roles.models;

import com.stock.inventario.permissions.models.Permission;
import com.stock.inventario.users.models.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Document("roles")
public class Role {
    private String id;
    private String name;

    @DBRef
    private List<User> users;

    @DBRef
    private List<Permission> permissions;
}
