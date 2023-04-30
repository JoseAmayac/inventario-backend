package com.stock.inventario.products.services;

import com.stock.inventario.products.interfaces.ProductRepository;
import com.stock.inventario.products.interfaces.ProductService;
import com.stock.inventario.products.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getProducts() {
        return this.productRepository.findAll();
    }

    @Override
    public Optional<Product> getProductById(String id) {
        return this.productRepository.findById(id);
    }

    @Override
    public Product createProduct(Product product) {
        return this.productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product, String id) throws Exception {
        Optional<Product> existingProduct = this.getProductById(id);
        if (!existingProduct.isPresent()){
            throw new Exception();
        }else{
            existingProduct.get().setName(product.getName());
            existingProduct.get().setDescription(product.getDescription());
            existingProduct.get().setPrice(product.getPrice());
            existingProduct.get().setCost(product.getCost());
            existingProduct.get().setStock(product.getStock());

            return this.productRepository.save(existingProduct.get());
        }
    }

    @Override
    public void deleteProduct(String id) {
        this.productRepository.deleteById(id);
    }


}
