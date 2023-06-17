package com.stock.inventario.supplierDocuments.interfaces;

import com.stock.inventario.supplierDocuments.dto.BasicSupplierDocumentDTO;
import com.stock.inventario.supplierDocuments.dto.SupplierDocumentCreationDTO;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface SupplierDocumentService {

    public List<BasicSupplierDocumentDTO> getSupplierDocuments();
    public BasicSupplierDocumentDTO getSupplierDocumentById(String id) throws ChangeSetPersister.NotFoundException;
    public BasicSupplierDocumentDTO createSupplierDocument(SupplierDocumentCreationDTO supplierDocumentCreation) throws ChangeSetPersister.NotFoundException;
    public BasicSupplierDocumentDTO updateSupplierDocument(SupplierDocumentCreationDTO supplierDocumentCreation, String id) throws ChangeSetPersister.NotFoundException;
    public void deleteSupplierDocument(String id) throws ChangeSetPersister.NotFoundException;
}
