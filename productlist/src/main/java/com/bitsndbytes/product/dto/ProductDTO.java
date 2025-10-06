package com.bitsndbytes.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(
        name = "Product",
        description = "Data Transfer Object for Product"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    @Schema(description = "Unique ID of the product", example = "101")
    private Long id;

    @Schema(description = "Name of the product", example = "iPhone 16 Pro")
    private String name;

    @Schema(description = "Description of the product", example = "Apple flagship phone - Pro version")
    private String description;

    @Schema(description = "Price of the product in USD", example = "1499.99")
    private Double price;

    @Schema(description = "ID of the category this product belongs to", example = "1")
    private Long categoryId;
}
