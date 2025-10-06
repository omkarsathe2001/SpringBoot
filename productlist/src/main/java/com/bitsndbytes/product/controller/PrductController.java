package com.bitsndbytes.product.controller;

import com.bitsndbytes.product.dto.ProductDTO;
import com.bitsndbytes.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Product REST API Operations",
        description = "CREATE, READ, UPDATE, DELETE operations for Product REST API"
)
@RestController
@RequestMapping("/api/products")
public class PrductController {

    @Autowired
    private ProductService productService;

    // Create Product
    @Operation(
            summary = "Create a new product",
            description = "Creates a new product with name, description, price, and categoryId"
    )
    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(
            @Parameter(description = "Product object to be created", required = true)
            @RequestBody ProductDTO dto) {
        return new ResponseEntity<>(productService.createProduct(dto), HttpStatus.CREATED);
    }

    // Get all Products
    @Operation(
            summary = "Get all products",
            description = "Fetches all products from the database"
    )
    @GetMapping
    public List<ProductDTO> getAllProducts() {
        return productService.getAllProducts();
    }

    // Get Product by ID
    @Operation(
            summary = "Get a product by ID",
            description = "Fetches a single product by its ID"
    )
    @GetMapping("/{id}")
    public ProductDTO getProductById(
            @Parameter(description = "ID of the product to fetch", required = true)
            @PathVariable Long id) {
        return productService.getProductById(id);
    }

    // Delete Product
    @Operation(
            summary = "Delete a product by ID",
            description = "Deletes a product from the database using its ID"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(
            @Parameter(description = "ID of the product to delete", required = true)
            @PathVariable Long id) {
        return ResponseEntity.ok(productService.deleteProductById(id));
    }

    // Update Product
    @Operation(
            summary = "Update an existing product by ID",
            description = "Updates product details using its ID"
    )
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(
            @Parameter(description = "ID of the product to update", required = true)
            @PathVariable Long id,
            @Parameter(description = "Updated product object", required = true)
            @RequestBody ProductDTO dto) {
        return ResponseEntity.ok(productService.updateProductById(id, dto));
    }
}
