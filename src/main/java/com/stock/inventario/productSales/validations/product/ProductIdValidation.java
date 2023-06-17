package com.stock.inventario.productSales.validations.product;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ProductIdValidator.class)
public @interface ProductIdValidation {
    public String message() default "No existe un producto con ese id";
    public Class<?>[] groups() default {};
    public Class<? extends Payload>[] payload() default {};
}
