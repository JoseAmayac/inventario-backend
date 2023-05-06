package com.stock.inventario.categories.mappers;

import com.stock.inventario.categories.dto.BasicCategoryDTO;
import com.stock.inventario.categories.dto.CategoryCreationDTO;
import com.stock.inventario.categories.models.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    public BasicCategoryDTO toBasicDTO(Category category);

    public Category toEntity(CategoryCreationDTO categoryCreationDTO);
}
