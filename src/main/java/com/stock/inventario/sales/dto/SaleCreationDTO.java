package com.stock.inventario.sales.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class SaleCreationDTO implements Serializable {
    private Date date;
    @Min(value = 0, message = "El valor total debe ser mayor o igual a cero")
    @NotNull(message = "El valor total es requerido")
    private Double total;
    private String clientId;
    @NotNull(message = "El vendedor es requerido")
    @NotBlank(message = "El vendedor no puede estar vacío")
    private String salesmanId;
}
