package com.stock.inventario.suppliers.interfaces;

import com.stock.inventario.suppliers.dto.BasicSupplierDTO;
import com.stock.inventario.suppliers.dto.SupplierCreationDTO;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface SupplierService {
    public List<BasicSupplierDTO> getAllSuppliers();
    public BasicSupplierDTO getSupplierById(String id) throws ChangeSetPersister.NotFoundException;
    public BasicSupplierDTO createSupplier(SupplierCreationDTO supplierCreationDTO);
    public BasicSupplierDTO updateSupplier(String id, SupplierCreationDTO supplierCreationDTO) throws ChangeSetPersister.NotFoundException;
    public void deleteSupplier(String id) throws ChangeSetPersister.NotFoundException;
}
