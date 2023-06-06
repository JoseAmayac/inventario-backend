package com.stock.inventario.permissions.repositories;

import com.stock.inventario.permissions.models.Permission;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PermissionRepository extends MongoRepository<Permission, String> {
}
