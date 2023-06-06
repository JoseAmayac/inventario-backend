package com.stock.inventario.permissions.models;

import com.stock.inventario.roles.models.Role;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Document("permissions")
public class Permission {
    @Id
    private String id;
    private String name;

    @DBRef
    List<Role> roles;
}
