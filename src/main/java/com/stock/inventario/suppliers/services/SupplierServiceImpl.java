package com.stock.inventario.suppliers.services;

import com.stock.inventario.suppliers.dto.BasicSupplierDTO;
import com.stock.inventario.suppliers.dto.SupplierCreationDTO;
import com.stock.inventario.suppliers.interfaces.SupplierService;
import com.stock.inventario.suppliers.mappers.SupplierMapper;
import com.stock.inventario.suppliers.models.Supplier;
import com.stock.inventario.suppliers.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class SupplierServiceImpl implements SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private SupplierMapper supplierMapper;

    @Override
    public List<BasicSupplierDTO> getAllSuppliers() {
        return this.supplierRepository.findAll()
                                      .stream()
                                      .map(supplier -> this.supplierMapper.toBasicDTO(supplier)).toList();
    }

    @Override
    public BasicSupplierDTO getSupplierById(String id) throws ChangeSetPersister.NotFoundException {
        Supplier supplier = this.supplierRepository.findById(id).orElseThrow(() -> new ChangeSetPersister.NotFoundException());
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
}
