package com.stock.inventario.categories.interfaces;

import com.stock.inventario.categories.dto.BasicCategoryDTO;
import com.stock.inventario.categories.dto.CategoryCreationDTO;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface CategoryService {
    public List<BasicCategoryDTO> getCategories();
    public BasicCategoryDTO getCategoryById(String id) throws ChangeSetPersister.NotFoundException;
    public BasicCategoryDTO create(CategoryCreationDTO categoryCreationDTO);
    public BasicCategoryDTO update(String id, CategoryCreationDTO categoryCreationDTO) throws ChangeSetPersister.NotFoundException;
    public void delete(String id) throws ChangeSetPersister.NotFoundException;
}
