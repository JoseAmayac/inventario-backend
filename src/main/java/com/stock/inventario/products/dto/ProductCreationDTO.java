package com.stock.inventario.products.dto;



import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public class ProductCreationDTO implements Serializable {

    @NotNull(message = "El nombre es requerido")
    private String name;
    @Min(value = 0, message = "El precio debe ser mayor o igual a cero")
    @NotNull(message = "El precio es requerido")
    private Double price;
    @Min(value = 0, message = "El costo debe ser mayor o igual a cero")
    @NotNull(message = "El costo es requerido")
    private Double cost;
    private String description;
    @Min(value = 0, message = "El stock debe ser mayor o igual a cero")
    @NotNull(message = "El stock es requerido")
    private Integer stock;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
