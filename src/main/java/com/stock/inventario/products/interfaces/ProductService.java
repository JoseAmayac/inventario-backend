package com.stock.inventario.products.interfaces;

import com.stock.inventario.products.dto.BasicProductDTO;
import com.stock.inventario.products.dto.ProductCreationDTO;
import org.springframework.data.crossstore.ChangeSetPersister;
import java.util.List;

public interface ProductService {
    public List<BasicProductDTO> getProducts();

    public BasicProductDTO getProductById(String id);

    public BasicProductDTO createProduct(ProductCreationDTO product) throws ChangeSetPersister.NotFoundException;

    public BasicProductDTO updateProduct(ProductCreationDTO product, String id) throws ChangeSetPersister.NotFoundException;

    public void deleteProduct(String id) throws ChangeSetPersister.NotFoundException;
}
