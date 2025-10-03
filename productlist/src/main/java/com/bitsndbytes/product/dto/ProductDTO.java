package com.bitsndbytes.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Long id;          // Use Long (wrapper) instead of long
    private String name;
    private String description;
    private Double price;
    private Long categoryId;  // also fix spelling (was CategeoryId before)
}
