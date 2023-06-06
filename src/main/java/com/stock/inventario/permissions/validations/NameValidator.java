package com.stock.inventario.permissions.validations;

import com.stock.inventario.permissions.models.Permission;
import com.stock.inventario.permissions.repositories.PermissionRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class NameValidator implements ConstraintValidator<NameValidation, String> {
    @Autowired
    private PermissionRepository permissionRepository;
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return this.permissionRepository.findByName(s).size() <= 0;
    }
}
