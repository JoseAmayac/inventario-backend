package com.stock.inventario.permissions.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target( { ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = NameValidator.class)
public @interface NameValidation {
    public String message() default "Ya existe un registro con ese nombre";
    public Class<?>[] groups() default {};
    public Class<? extends Payload>[] payload() default {};
}
