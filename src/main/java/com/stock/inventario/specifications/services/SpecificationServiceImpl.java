package com.stock.inventario.specifications.services;

import com.stock.inventario.specifications.dto.BasicSpecificationDTO;
import com.stock.inventario.specifications.dto.SpecificationCreationDTO;
import com.stock.inventario.specifications.interfaces.SpecificationService;
import com.stock.inventario.specifications.mappers.SpecificationMapper;
import com.stock.inventario.specifications.models.Specification;
import com.stock.inventario.specifications.repositories.SpecificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecificationServiceImpl implements SpecificationService {
    @Autowired
    private SpecificationRepository specificationRepository;
    @Autowired
    private SpecificationMapper specificationMapper;
    @Override
    public List<BasicSpecificationDTO> getAllSpecifications() {
        return this.specificationRepository.findAll()
                                           .stream()
                                           .map(specification -> this.specificationMapper.toBasicDTO(specification))
                                           .toList();
    }

    @Override
    public BasicSpecificationDTO getSpecificationById(String id) throws ChangeSetPersister.NotFoundException {
        Specification specification = this.specificationRepository.findById(id).orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        return specificationMapper.toBasicDTO(specification);
    }

    @Override
    public BasicSpecificationDTO createSpecification(SpecificationCreationDTO specificationCreationDTO) {
        Specification specification = this.specificationRepository.save(this.specificationMapper.toEntity(specificationCreationDTO));
        return this.specificationMapper.toBasicDTO(specification);
    }

    @Override
    public BasicSpecificationDTO updateSpecification(String id, SpecificationCreationDTO specificationCreationDTO) throws ChangeSetPersister.NotFoundException {
        Specification specification = this.specificationMapper.toEntity(this.getSpecificationById(id));
        specification.setId(id);
        Specification specificationToUpdate = this.specificationMapper.toEntity(specificationCreationDTO);
        specificationToUpdate.setId(specification.getId());
        return this.specificationMapper.toBasicDTO(this.specificationRepository.save(specificationToUpdate));
    }

    @Override
    public void deleteSpecification(String id) throws ChangeSetPersister.NotFoundException {
        BasicSpecificationDTO basicSpecificationDTO = this.getSpecificationById(id);
        this.specificationRepository.deleteById(basicSpecificationDTO.getId());
    }
}
