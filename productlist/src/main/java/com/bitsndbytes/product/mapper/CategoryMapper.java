package com.bitsndbytes.product.mapper;

import com.bitsndbytes.product.dto.CategoryDTO;
import com.bitsndbytes.product.entity.Category;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class CategoryMapper {

    // DTO → Entity
    public static Category toCategoryEntity(CategoryDTO dto) {
        if (dto == null) return null;

        Category category = new Category();

        // only set id if present (for update case)
        if (dto.getId() != null) {
            category.setId(dto.getId());
        }

        category.setName(dto.getName());

        if (dto.getProducts() != null) {
            category.setProducts(
                    dto.getProducts()
                            .stream()
                            .map(ProductMapper::toProductEntity)
                            .collect(Collectors.toList())
            );
        }

        return category;
    }

    // Entity → DTO
    public static CategoryDTO toCategoryDTO(Category category) {
        if (category == null) return null;

        CategoryDTO dto = new CategoryDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());

        if (category.getProducts() != null) {
            dto.setProducts(
                    category.getProducts()
                            .stream()
                            .map(ProductMapper::toProductDTO)
                            .collect(Collectors.toList())
            );

        }

        return dto;
    }
}
