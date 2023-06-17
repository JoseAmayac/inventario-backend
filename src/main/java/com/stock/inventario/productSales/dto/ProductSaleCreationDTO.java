package com.stock.inventario.productSales.dto;

import com.stock.inventario.productSales.validations.product.ProductIdValidation;
import com.stock.inventario.productSales.validations.sale.SaleIdValidation;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ProductSaleCreationDTO implements Serializable {
    @NotNull(message = "El producto es obligatorio")
    @NotBlank(message = "El producto no puede estar vacío")
    @ProductIdValidation
    private String productId;
    @NotNull(message = "La venta es obligatoria")
    @NotBlank(message = "La venta no puede estar vacía")
    @SaleIdValidation
    private String saleId;
    @NotNull(message = "El valor es obligatorio")
    @NotBlank(message = "El valor no puede estar vacío")
    @Min(value = 0, message = "El valor mínimo debe ser 0")
    private Double value;
    @NotNull(message = "La cantidad es obligatoria")
    @NotBlank(message = "La cantidad no puede estar vacía")
    @Min(value = 0, message = "El valor mínimo debe ser 0.1")
    private Double quantity;
}
