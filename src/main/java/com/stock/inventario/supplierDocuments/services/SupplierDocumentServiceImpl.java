package com.stock.inventario.supplierDocuments.services;

import com.stock.inventario.supplierDocuments.dto.BasicSupplierDocumentDTO;
import com.stock.inventario.supplierDocuments.dto.SupplierDocumentCreationDTO;
import com.stock.inventario.supplierDocuments.interfaces.SupplierDocumentService;
import com.stock.inventario.supplierDocuments.mappers.SupplierDocumentMapper;
import com.stock.inventario.supplierDocuments.models.SupplierDocument;
import com.stock.inventario.supplierDocuments.repositories.SuplierDocumentRepository;
import com.stock.inventario.suppliers.models.Supplier;
import com.stock.inventario.suppliers.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierDocumentServiceImpl implements SupplierDocumentService {
    @Autowired
    private SuplierDocumentRepository suplierDocumentRepository;
    @Autowired
    private SupplierDocumentMapper supplierDocumentMapper;
    @Autowired
    private SupplierRepository supplierRepository;
    @Override
    public List<BasicSupplierDocumentDTO> getSupplierDocuments() {
        return this.suplierDocumentRepository.findAll()
                                             .stream()
                                             .map(supplierDocument -> this.supplierDocumentMapper.toBasicDTO(supplierDocument)).toList();
    }

    @Override
    public BasicSupplierDocumentDTO getSupplierDocumentById(String id) throws ChangeSetPersister.NotFoundException {
        SupplierDocument supplierDocument = this.suplierDocumentRepository.findById(id).orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        return this.supplierDocumentMapper.toBasicDTO(supplierDocument);
    }

    @Override
    public BasicSupplierDocumentDTO createSupplierDocument(SupplierDocumentCreationDTO supplierDocumentCreation) throws ChangeSetPersister.NotFoundException {
        Supplier supplier = this.supplierRepository.findById(supplierDocumentCreation.getSupplierId()).orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        SupplierDocument supplierDocument = this.supplierDocumentMapper.toEntity(supplierDocumentCreation);
        supplierDocument.setSupplier(supplier);
        return this.supplierDocumentMapper.toBasicDTO(this.suplierDocumentRepository.save(supplierDocument));
    }

    @Override
    public BasicSupplierDocumentDTO updateSupplierDocument(SupplierDocumentCreationDTO supplierDocumentCreation, String id) throws ChangeSetPersister.NotFoundException {
        this.getSupplierDocumentById(id);
        Supplier supplier = this.supplierRepository.findById(supplierDocumentCreation.getSupplierId()).orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        SupplierDocument supplierDocument = this.supplierDocumentMapper.toEntity(supplierDocumentCreation);
        supplierDocument.setId(id);
        supplierDocument.setSupplier(supplier);
        return this.supplierDocumentMapper.toBasicDTO(this.suplierDocumentRepository.save(supplierDocument));
    }

    @Override
    public void deleteSupplierDocument(String id) throws ChangeSetPersister.NotFoundException {
        this.getSupplierDocumentById(id);
        this.suplierDocumentRepository.deleteById(id);
    }
}
