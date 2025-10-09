package com.bitsndbytes.product.service;

import com.bitsndbytes.product.dto.CategoryDTO;
import com.bitsndbytes.product.dto.ProductDTO;
import com.bitsndbytes.product.entity.Category;
import com.bitsndbytes.product.entity.Product;
import com.bitsndbytes.product.exception.CategoryNotFoundException;
import com.bitsndbytes.product.exception.ProductAlreadyExitsException;
import com.bitsndbytes.product.exception.ProductNotFoundException;
import com.bitsndbytes.product.mapper.ProductMapper;
import com.bitsndbytes.product.repository.CategoryRepository;
import com.bitsndbytes.product.repository.ProductRepository;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

//    Create Product
    public ProductDTO createProduct(ProductDTO dto){
        /**
         *  name, description, price, categoryId
         * **/
        // Check if product name already exists
        productRepository.findByName(dto.getName())
                .ifPresent(p -> {
                    throw new ProductAlreadyExitsException("Product with Name = " + dto.getName() + " already exists.");
                });

        // Check if category exists
        categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException("Category Id = " + dto.getCategoryId() + " not found."));

        // DTO -> Entity
        Product product = ProductMapper.toProductEntity(dto);

        // Save product
        product = productRepository.save(product);

        // Entity -> DTO
        return ProductMapper.toProductDTO(product);
    }

//    Get all Product
    public List<ProductDTO> getAllProducts(){
        return productRepository.findAll()
                .stream()
                .map(ProductMapper::toProductDTO)
                .toList();
    }


//    Get Product by id
    public ProductDTO getProductById(Long id){
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new ProductNotFoundException("Product Id = "+id+" not found..."));
        return ProductMapper.toProductDTO(product);
    }

//    Delete Product
    public String deleteProductById(Long id){
        if (!productRepository.existsById(id)){
            throw new ProductNotFoundException("Product with ID " + id + " not found!");
        }
        productRepository.deleteById(id);
        return "Product "+id+" has been deleted successfully...";
    }


//    Update Product
    public ProductDTO updateProductById(Long id, ProductDTO dto){
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new ProductNotFoundException("Product Id = "+id+" not found..."));

        // Update existing fields
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());

        // If category is being updated
        if (dto.getCategoryId() != null){
            Category category = categoryRepository.findById(dto.getCategoryId())
                    .orElseThrow(()-> new CategoryNotFoundException("Category Id = "+dto.getCategoryId()+" not found..."));
            product.setCategory(category);
        }

        product = productRepository.save(product);
        return ProductMapper.toProductDTO(product);
    }
}
