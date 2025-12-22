package com.petshop.PetShop_management.service.impl;

import com.petshop.PetShop_management.dto.CreateProductDTO;
import com.petshop.PetShop_management.entity.Product;
import com.petshop.PetShop_management.entity.Category;
import com.petshop.PetShop_management.repository.ProductRepository;
import com.petshop.PetShop_management.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long product_id) {
        return productRepository.findById(product_id);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteById(Long product_id) {
        productRepository.deleteById(product_id);
    }

    @Override
    public List<Product> findByNameContaining(String product_name) {
        return productRepository.findByProductNameContainingIgnoreCase(product_name);
    }


    public Product createProductWithCategory(CreateProductDTO dto, Category category) {
        Product product = new Product();
        product.setProductName(dto.getName());
        product.setDescript(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setRemnant(dto.getQuantity());
        product.setCategory(category);
        return save(product);
    }

}

