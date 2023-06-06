package com.stock.inventario.permissions.models;

import com.stock.inventario.roles.models.Role;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Getter
@Setter
@Document("permissions")
public class Permission {
    @Id
    private String id;

    @Indexed(unique = true)

    private String name;

    @DBRef
    List<Role> roles;
}
