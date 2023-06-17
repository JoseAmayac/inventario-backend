package com.stock.inventario.productSales.validations.sale;

import com.stock.inventario.sales.repositories.SaleRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class SaleIdValidator implements ConstraintValidator<SaleIdValidation, String> {
    @Autowired
    private SaleRepository saleRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return this.saleRepository.findById( value ).orElse(null ) != null;
    }
}
