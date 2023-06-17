package com.stock.inventario.sales.dto;

import com.stock.inventario.clients.dto.BasicClientDTO;
import com.stock.inventario.users.dto.BasicUserDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasicSaleDTO extends SaleCreationDTO{
    private String id;
    private BasicClientDTO client;
    private BasicUserDTO salesman;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
}
