package com.stock.inventario.brands.mappers;

import com.stock.inventario.brands.dto.BasicBrandDTO;
import com.stock.inventario.brands.dto.BrandCreationDTO;
import com.stock.inventario.brands.models.Brand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BrandMapper {
    public Brand toEntity(BrandCreationDTO brandCreationDTO);
    public BasicBrandDTO toBasicDTO(Brand brand);
}
