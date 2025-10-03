package com.bitsndbytes.product.service;

import com.bitsndbytes.product.dto.CategoryDTO;
import com.bitsndbytes.product.dto.ProductDTO;
import com.bitsndbytes.product.entity.Category;
import com.bitsndbytes.product.entity.Product;
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
        categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(()-> new RuntimeException("Category Id does not found..."));

        //DTO -> Entity
        Product product = ProductMapper.toProductEntity(dto);
        product = productRepository.save(product);

        //Entity -> DTO
        ProductDTO productDTO = ProductMapper.toProductDTO(product);
        return productDTO;

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
                .orElseThrow(()-> new RuntimeException("Product Id not found..."));
        return ProductMapper.toProductDTO(product);
    }

//    Delete Product
    public String deleteProductById(Long id){
        productRepository.deleteById(id);
        return "Product "+id+" has been deleted successfully...";
    }


//    Update Product
    public ProductDTO updateProductById(Long id, ProductDTO dto){
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Product Id not found..."));

        // Update existing fields
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());

        // If category is being updated
        if (dto.getCategoryId() != null){
            Category category = categoryRepository.findById(dto.getCategoryId())
                    .orElseThrow(()-> new RuntimeException("Category not found with id " + dto.getCategoryId()));
            product.setCategory(category);
        }

        product = productRepository.save(product);
        return ProductMapper.toProductDTO(product);
    }
}
