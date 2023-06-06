package com.stock.inventario.permissions.repositories;

import com.stock.inventario.permissions.models.Permission;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionRepository extends MongoRepository<Permission, String> {

    public List<Permission> findByName(String name);
}
