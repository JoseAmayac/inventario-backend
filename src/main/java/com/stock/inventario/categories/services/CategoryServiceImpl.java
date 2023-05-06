package com.stock.inventario.categories.services;

import com.stock.inventario.categories.dto.BasicCategoryDTO;
import com.stock.inventario.categories.dto.CategoryCreationDTO;
import com.stock.inventario.categories.interfaces.CategoryService;
import com.stock.inventario.categories.mappers.CategoryMapper;
import com.stock.inventario.categories.models.Category;
import com.stock.inventario.categories.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public List<BasicCategoryDTO> getCategories() {
        return this.categoryRepository.findAll()
                                      .stream()
                                      .map(category -> this.categoryMapper.toBasicDTO(category))
                                      .toList();
    }

    @Override
    public BasicCategoryDTO getCategoryById(String id) throws ChangeSetPersister.NotFoundException {
        Category category = this.categoryRepository.findById(id).orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        return this.categoryMapper.toBasicDTO(category);
    }

    @Override
    public BasicCategoryDTO create(CategoryCreationDTO categoryCreationDTO) {
        return this.categoryMapper.toBasicDTO(this.categoryRepository.save(this.categoryMapper.toEntity(categoryCreationDTO)));
    }

    @Override
    public BasicCategoryDTO update(String id, CategoryCreationDTO categoryCreationDTO) throws ChangeSetPersister.NotFoundException {
        BasicCategoryDTO category = this.getCategoryById(id);
        Category categoryToUpdate = this.categoryMapper.toEntity(categoryCreationDTO);
        categoryToUpdate.setId(category.getId());
        return this.categoryMapper.toBasicDTO(this.categoryRepository.save(categoryToUpdate));
    }

    @Override
    public void delete(String id) throws ChangeSetPersister.NotFoundException {
        BasicCategoryDTO categoryDTO = this.getCategoryById(id);
        this.categoryRepository.deleteById(categoryDTO.getId());
    }
}
