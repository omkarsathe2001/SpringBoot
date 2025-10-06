package com.bitsndbytes.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Schema(name = "Category", description = "Data Transfer Object for Category")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

    @Schema(description = "Unique ID of the category", example = "1")
    private Long id;

    @Schema(description = "Name of the category", example = "Electronics")
    private String name;

    @Schema(description = "List of products under this category")
    private List<ProductDTO> products = new ArrayList<>();
}
