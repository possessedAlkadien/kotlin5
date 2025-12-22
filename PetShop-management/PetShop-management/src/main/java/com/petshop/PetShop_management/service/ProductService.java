package com.petshop.PetShop_management.service;

import com.petshop.PetShop_management.entity.Product;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAll();
    Optional<Product> findById(Long product_id);
    Product save(Product product);
    void deleteById(Long product_id);
    List<Product> findByNameContaining(String product_name);
}
