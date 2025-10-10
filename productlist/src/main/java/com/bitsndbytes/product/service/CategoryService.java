package com.bitsndbytes.product.service;

import com.bitsndbytes.product.dto.CategoryDTO;
import com.bitsndbytes.product.entity.Category;
import com.bitsndbytes.product.exception.CategoryAlreadyExitsException;
import com.bitsndbytes.product.exception.CategoryNotFoundException;
import com.bitsndbytes.product.mapper.CategoryMapper;
import com.bitsndbytes.product.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    //Create Categories
    public CategoryDTO createCategory(CategoryDTO categoryDTO){
        categoryRepository.findByName(categoryDTO.getName())
                .ifPresent(c->{
                    throw new CategoryAlreadyExitsException("Category "+categoryDTO.getName()+" Already Exists");
                });
        Category category = CategoryMapper.toCategoryEntity(categoryDTO);
        category = categoryRepository.save(category);
        return CategoryMapper.toCategoryDTO(category);
    }

    //Get all Categories
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(CategoryMapper::toCategoryDTO)
                .toList();
    }

    //Get Categories by id
    public CategoryDTO getCategoryById(Long id){
     Category category = categoryRepository.findById(id)
                .orElseThrow(()-> new CategoryNotFoundException("Category Id = "+id+" not found..."));
     return CategoryMapper.toCategoryDTO(category);
    }

    //Delete Categories
    public String deleteCategoryById(Long id){
        if (!categoryRepository.existsById(id)){
            throw new CategoryNotFoundException("Category Id = "+id+" not found...");
        }
        categoryRepository.deleteById(id);
        return "Category "+id+" has been deleted successfully...";
    }

    //Update Categories
    public CategoryDTO updateCategory(Long id, CategoryDTO dto){
        Category category = categoryRepository.findById(id)
                .orElseThrow(()-> new CategoryNotFoundException("Category Id = "+id+" not found..."));

        category.setName(dto.getName());

        category = categoryRepository.save(category);
        return CategoryMapper.toCategoryDTO(category);
    }
}
