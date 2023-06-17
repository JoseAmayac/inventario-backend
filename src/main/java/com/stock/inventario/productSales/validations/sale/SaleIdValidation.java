package com.stock.inventario.productSales.validations.sale;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target( { ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = SaleIdValidator.class)
public @interface SaleIdValidation {
    public String message() default "No existe una venta con este id";
    public Class<?>[] groups() default {};
    public Class<? extends Payload>[] payload() default {};
}
