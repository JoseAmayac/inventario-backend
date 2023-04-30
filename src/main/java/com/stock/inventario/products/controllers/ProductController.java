package com.stock.inventario.products.controllers;


import com.stock.inventario.products.interfaces.ProductService;
import com.stock.inventario.products.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("")
    public List<Product> index(){
        return this.productService.getProducts();
    }

    @GetMapping("/{id}")
    public Optional<Product> show(@PathVariable String id){
        return this.productService.getProductById(id);
    }

    @PostMapping("")
    public Product store(@RequestBody Product product){
        return this.productService.createProduct(product);
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable String id, @RequestBody Product product){
        try{
            return this.productService.updateProduct(product, id);
        }catch (Exception e){
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id){
        this.productService.deleteProduct(id);
    }
}
