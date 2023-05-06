package com.stock.inventario.categories.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Data
@Document("categories")
public class Category {
    @Id
    private String id;
    private String name;
}
