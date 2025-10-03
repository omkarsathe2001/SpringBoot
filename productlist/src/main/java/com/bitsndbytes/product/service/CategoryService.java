package com.bitsndbytes.product.service;

import com.bitsndbytes.product.dto.CategoryDTO;
import com.bitsndbytes.product.entity.Category;
import com.bitsndbytes.product.mapper.CategoryMapper;
import com.bitsndbytes.product.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    //Create Categories
    public CategoryDTO createCategory(CategoryDTO categoryDTO){
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
                .orElseThrow(()-> new RuntimeException("Category Id not found..."));
     return CategoryMapper.toCategoryDTO(category);
    }

    //Delete Categories
    public String deleteCategoryById(Long id){
        categoryRepository.deleteById(id);
        return "Category "+id+" has been deleted successfully...";
    }

    //Update Categories
    public CategoryDTO updateCategory(Long id, CategoryDTO dto){
        Category category = categoryRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Category Id not found..."));

        category.setName(dto.getName());

        category = categoryRepository.save(category);
        return CategoryMapper.toCategoryDTO(category);
    }
}
