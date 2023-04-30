package com.stock.inventario.products.interfaces;

import com.stock.inventario.products.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    public List<Product> getProducts();

    public Optional<Product> getProductById(String id);

    public Product createProduct(Product product);

    public Product updateProduct(Product product, String id) throws Exception;

    public void deleteProduct(String id);
}
