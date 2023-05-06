package com.stock.inventario.categories.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class BasicCategoryDTO extends CategoryCreationDTO{
    private String id;
}
