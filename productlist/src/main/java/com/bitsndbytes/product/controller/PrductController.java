package com.bitsndbytes.product.controller;

import com.bitsndbytes.product.dto.ProductDTO;
import com.bitsndbytes.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class PrductController {

    @Autowired
    private ProductService productService;

//    Create Product
    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO dto) {
        return new ResponseEntity<>(productService.createProduct(dto), HttpStatus.CREATED);
    }


//    Get all Product
    @GetMapping
    public List<ProductDTO> getAllProducts(){
        return productService.getAllProducts();
    }

//    Get Product by id
    @GetMapping("/{id}")
    public ProductDTO getProductById(@PathVariable Long id)
    {
        return productService.getProductById(id);
    }

//    Delete Product
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id){
        return ResponseEntity.ok(productService.deleteProductById(id));
    }

//    Update Product
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id,
                                                    @RequestBody ProductDTO dto){
        return ResponseEntity.ok(productService.updateProductById(id, dto));
    }
}