package com.stock.inventario.specifications.mappers;

import com.stock.inventario.specifications.dto.BasicSpecificationDTO;
import com.stock.inventario.specifications.dto.SpecificationCreationDTO;
import com.stock.inventario.specifications.models.Specification;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SpecificationMapper {
    public Specification toEntity(SpecificationCreationDTO specificationCreationDTO);
    public BasicSpecificationDTO toBasicDTO(Specification specification);
}
