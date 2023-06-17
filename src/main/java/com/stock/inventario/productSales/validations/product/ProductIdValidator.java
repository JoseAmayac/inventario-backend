package com.stock.inventario.productSales.validations.product;

import com.stock.inventario.products.repositories.ProductRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductIdValidator implements ConstraintValidator<ProductIdValidation, String> {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return this.productRepository.findById( value ).orElse(null ) != null;
    }
}
