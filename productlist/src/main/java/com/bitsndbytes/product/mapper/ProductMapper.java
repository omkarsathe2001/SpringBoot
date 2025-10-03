package com.bitsndbytes.product.mapper;

import com.bitsndbytes.product.dto.ProductDTO;
import com.bitsndbytes.product.entity.Category;
import com.bitsndbytes.product.entity.Product;

public class ProductMapper {

    // DTO → Entity
    public static Product toProductEntity(ProductDTO dto) {
        if (dto == null) return null;

        Product product = new Product();

        // Only set id if provided (update case)
        if (dto.getId() != null) {
            product.setId(dto.getId());
        }

        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());

        if (dto.getCategoryId() != null) {
            Category category = new Category();
            category.setId(dto.getCategoryId());
            product.setCategory(category);
        }

        return product;
    }


    // Entity → DTO
    public static ProductDTO toProductDTO(Product product) {
        if (product == null) return null;

        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getCategory() != null ? product.getCategory().getId() : null
        );
    }
}
