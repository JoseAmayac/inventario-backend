package com.stock.inventario.specifications.interfaces;

import com.stock.inventario.specifications.dto.BasicSpecificationDTO;
import com.stock.inventario.specifications.dto.SpecificationCreationDTO;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface SpecificationService {
    public List<BasicSpecificationDTO> getAllSpecifications();
    public BasicSpecificationDTO getSpecificationById(String id) throws ChangeSetPersister.NotFoundException;
    public  BasicSpecificationDTO createSpecification(SpecificationCreationDTO specificationCreationDTO);
    public BasicSpecificationDTO updateSpecification(String id, SpecificationCreationDTO specificationCreationDTO) throws ChangeSetPersister.NotFoundException;
    public void deleteSpecification(String id) throws ChangeSetPersister.NotFoundException;
}
