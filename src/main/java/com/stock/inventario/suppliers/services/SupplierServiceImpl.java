package com.stock.inventario.suppliers.services;

import com.stock.inventario.products.models.Product;
import com.stock.inventario.products.repositories.ProductRepository;
import com.stock.inventario.suppliers.dto.BasicSupplierDTO;
import com.stock.inventario.suppliers.dto.SupplierCreationDTO;
import com.stock.inventario.suppliers.interfaces.SupplierService;
import com.stock.inventario.suppliers.mappers.SupplierMapper;
import com.stock.inventario.suppliers.models.Supplier;
import com.stock.inventario.suppliers.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class SupplierServiceImpl implements SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private SupplierMapper supplierMapper;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<BasicSupplierDTO> getAllSuppliers() {
        return this.supplierRepository.findAll()
                                      .stream()
                                      .map(supplier -> {
                                          List<Product> products = this.productRepository.findBySupplierId( supplier.getId() );
                                          supplier.setProducts( products );
                                          return this.supplierMapper.toBasicDTO(supplier);
                                      }).toList();
    }

    @Override
    public BasicSupplierDTO getSupplierById(String id) throws ChangeSetPersister.NotFoundException {
        Supplier supplier = this.supplierRepository.findById(id).orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        List<Product> products = new ArrayList<>();
        for (Product product : supplier.getProducts()){
            Product productDB = this.productRepository.findById(product.getId()).orElseThrow(() -> new ChangeSetPersister.NotFoundException());
            products.add( productDB );
        }
        supplier.setProducts( products );
        return this.supplierMapper.toBasicDTO(supplier);
    }

    @Override
    public BasicSupplierDTO createSupplier(SupplierCreationDTO supplierCreationDTO) {
        Supplier supplier = this.supplierRepository.save(this.supplierMapper.toEntity(supplierCreationDTO));
        return this.supplierMapper.toBasicDTO(supplier);
    }

    @Override
    public BasicSupplierDTO updateSupplier(String id, SupplierCreationDTO supplierCreationDTO) throws ChangeSetPersister.NotFoundException {
        Supplier supplier = this.supplierMapper.toEntity(this.getSupplierById(id));
        supplier.setId(id);
        Supplier supplierToUpdate = this.supplierMapper.toEntity(supplierCreationDTO);
        supplierToUpdate.setId(supplier.getId());
        return this.supplierMapper.toBasicDTO(this.supplierRepository.save(supplierToUpdate));
    }

    @Override
    public void deleteSupplier(String id) throws ChangeSetPersister.NotFoundException {
        BasicSupplierDTO basicSupplierDTO = this.getSupplierById(id);
        this.supplierRepository.deleteById(basicSupplierDTO.getId());
    }

    @Override
    public void associateProduct(String supplierId, String productId) throws ChangeSetPersister.NotFoundException{
//        Supplier supplier = this.supplierRepository.findById( supplierId ).orElseThrow(() -> new ChangeSetPersister.NotFoundException());
//        Product product = this.productRepository.findById( productId ).orElseThrow(() -> new ChangeSetPersister.NotFoundException());
//
//        if ( supplier.getProducts() == null ){
//            supplier.setProducts(new ArrayList<>());
//        }
//
//        product.setSupplierId( supplier.getId() );
//        this.productRepository.save( product );
//
//
//        supplier.getProducts().add( product );
//        this.supplierRepository.save( supplier );
//
//        product = this.productRepository.findById( productId ).orElseThrow(() -> new ChangeSetPersister.NotFoundException());
//        System.out.println( product.getSupplierId());

    }
}
