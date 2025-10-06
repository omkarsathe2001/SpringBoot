package com.bitsndbytes.product.controller;

import com.bitsndbytes.product.dto.CategoryDTO;
import com.bitsndbytes.product.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Category REST API Operations",
        description = "CREATE, READ, UPDATE, DELETE operations for Category REST API"
)
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // Create Category
    @Operation(
            summary = "Create a new category",
            description = "REST API to create a new category"
    )
    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(
            @Parameter(description = "Category object to be created", required = true)
            @RequestBody CategoryDTO categoryDTO) {
        return new ResponseEntity<>(categoryService.createCategory(categoryDTO), HttpStatus.CREATED);
    }

    // Get all Categories
    @Operation(
            summary = "Get all categories",
            description = "REST API to fetch all categories"
    )
    @GetMapping
    public List<CategoryDTO> getAllCategories() {
        return categoryService.getAllCategories();
    }

    // Get Category by ID
    @Operation(
            summary = "Get category by ID",
            description = "Fetch a single category by its ID"
    )
    @GetMapping("/{id}")
    public CategoryDTO getCategoryById(
            @Parameter(description = "ID of the category to fetch", required = true)
            @PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }

    // Delete Category
    @Operation(
            summary = "Delete category by ID",
            description = "Delete a category using its ID"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(
            @Parameter(description = "ID of the category to delete", required = true)
            @PathVariable Long id) {
        categoryService.deleteCategoryById(id);
        return ResponseEntity.ok("Category deleted successfully with id " + id);
    }

    // Update Category
    @Operation(
            summary = "Update an existing category",
            description = "Update category details using its ID"
    )
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(
            @Parameter(description = "ID of the category to update", required = true)
            @PathVariable Long id,
            @Parameter(description = "Updated category object", required = true)
            @RequestBody CategoryDTO categoryDTO) {
        return ResponseEntity.ok(categoryService.updateCategory(id, categoryDTO));
    }
}
