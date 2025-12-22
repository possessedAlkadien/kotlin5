package com.petshop.PetShop_management.controller;

import com.petshop.PetShop_management.dto.ProductDTO;
import com.petshop.PetShop_management.dto.CreateProductDTO;
import com.petshop.PetShop_management.entity.Product;
import com.petshop.PetShop_management.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<Product> products = productService.findAll();
        List<ProductDTO> productDTOs = products.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(productDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        if (product.isPresent()) {
            return ResponseEntity.ok(convertToDTO(product.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody CreateProductDTO createProductDTO) {
        try {
            Product product = convertToEntity(createProductDTO);
            Product savedProduct = productService.save(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(savedProduct));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @Valid @RequestBody CreateProductDTO createProductDTO) {
        Optional<Product> existingProduct = productService.findById(id);
        if (existingProduct.isPresent()) {
            try {
                Product product = convertToEntity(createProductDTO);
                product.setProductId(id);
                Product updatedProduct = productService.save(product);
                return ResponseEntity.ok(convertToDTO(updatedProduct));
            } catch (Exception e) {
                return ResponseEntity.badRequest().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        if (product.isPresent()) {
            productService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductDTO>> searchProducts(@RequestParam String name) {
        List<Product> products = productService.findByNameContaining(name);
        List<ProductDTO> productDTOs = products.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(productDTOs);
    }

    private ProductDTO convertToDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getProductId());
        dto.setProductName(product.getProductName());
        dto.setDescription(product.getDescript());
        dto.setPrice(product.getPrice());
        dto.setRemnant(product.getRemnant());
        return dto;
    }

    private Product convertToEntity(CreateProductDTO dto) {
        Product product = new Product();
        product.setProductName(dto.getName());
        product.setDescript(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setRemnant(dto.getQuantity());
        return product;
    }
}

