package com.stock.inventario.categories.controllers;

import com.stock.inventario.categories.dto.BasicCategoryDTO;
import com.stock.inventario.categories.dto.CategoryCreationDTO;
import com.stock.inventario.categories.interfaces.CategoryService;
import com.stock.inventario.common.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping
    public ResponseEntity<ApiResponse> index(){
        try{
            List<BasicCategoryDTO> categories = this.categoryService.getCategories();
            return new ResponseEntity<>(new ApiResponse(true, categories), HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(new ApiResponse(false, ex.getMessage(), ex), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> show(@PathVariable String id){
        try{
            BasicCategoryDTO category = this.categoryService.getCategoryById(id);
            return new ResponseEntity<>(new ApiResponse(true, category), HttpStatus.OK);
        }catch (ChangeSetPersister.NotFoundException ex){
            return new ResponseEntity<>(new ApiResponse(false, "Recurso no encontrado"), HttpStatus.NOT_FOUND);
        }catch (Exception ex){
            return new ResponseEntity<>(new ApiResponse(false, ex.getMessage(), ex), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse> store(@Valid @RequestBody CategoryCreationDTO categoryCreationDTO){
        try{
            BasicCategoryDTO category = this.categoryService.create(categoryCreationDTO);
            return new ResponseEntity<>(new ApiResponse(true, category), HttpStatus.CREATED);
        }catch (Exception ex){
            return new ResponseEntity<>(new ApiResponse(false, ex.getMessage(), ex), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable String id, @Valid @RequestBody CategoryCreationDTO categoryCreationDTO){
        try{
            BasicCategoryDTO category = this.categoryService.update(id, categoryCreationDTO);
            return new ResponseEntity<>(new ApiResponse(true, category), HttpStatus.OK);
        }catch (ChangeSetPersister.NotFoundException ex){
            return new ResponseEntity<>(new ApiResponse(false, "Recurso no encontrado"), HttpStatus.NOT_FOUND);
        }catch (Exception ex){
            return new ResponseEntity<>(new ApiResponse(false, ex.getMessage(), ex), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable String id){
        try{
            this.categoryService.delete(id);
            return new ResponseEntity<>(new ApiResponse(true, "Categoría eliminada correctamente"), HttpStatus.OK);
        }catch (ChangeSetPersister.NotFoundException ex){
            return new ResponseEntity<>(new ApiResponse(false, "Recurso no encontrado"), HttpStatus.NOT_FOUND);
        }catch (Exception ex){
            return new ResponseEntity<>(new ApiResponse(false, ex.getMessage(), ex), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
