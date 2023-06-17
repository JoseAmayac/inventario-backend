package com.stock.inventario.products.services;

import com.stock.inventario.products.dto.BasicProductDTO;
import com.stock.inventario.products.dto.ProductCreationDTO;
import com.stock.inventario.products.repositories.ProductRepository;
import com.stock.inventario.products.interfaces.ProductService;
import com.stock.inventario.products.mappers.ProductMapper;
import com.stock.inventario.products.models.Product;
import com.stock.inventario.suppliers.models.Supplier;
import com.stock.inventario.suppliers.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<BasicProductDTO> getProducts() {
        return this.productRepository.findAll()
                                     .stream()
                                     .map(product -> this.productMapper.toBasicDto(product)).toList();
    }

    @Override
    public BasicProductDTO getProductById(String id) {
        Optional<Product> productOpt = this.productRepository.findById(id);
        if (productOpt.isPresent()){
            return this.productMapper.toBasicDto(productOpt.get());
        }

        return null;
    }

    @Override
    public BasicProductDTO createProduct(ProductCreationDTO productDTO) throws ChangeSetPersister.NotFoundException {
        Supplier supplier = this.supplierRepository.findById( productDTO.getSupplierId() ).orElseThrow(()->new ChangeSetPersister.NotFoundException());
        Product product = this.productMapper.toEntity(productDTO);
        product.setSupplier( supplier );
        return this.productMapper.toBasicDto(this.productRepository.save(product));
    }

    @Override
    public BasicProductDTO updateProduct(ProductCreationDTO productCreationDTO, String id) throws ChangeSetPersister.NotFoundException {
        BasicProductDTO product = this.getProductById(id);
        if (product == null){
            throw new ChangeSetPersister.NotFoundException();
        }else{
            Product productToUpdate = this.productMapper.toEntity(productCreationDTO);
            productToUpdate.setId(id);

            return this.productMapper.toBasicDto(this.productRepository.save(productToUpdate));
        }
    }

    @Override
    public void deleteProduct(String id) throws ChangeSetPersister.NotFoundException {
        BasicProductDTO productExisting = this.getProductById(id);
        if ( productExisting == null ){
            throw new ChangeSetPersister.NotFoundException();
        }
        this.productRepository.deleteById(id);
    }
}
