package com.petshop.PetShop_management.repository;

import com.petshop.PetShop_management.entity.Product;
import com.petshop.PetShop_management.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByProductNameContainingIgnoreCase(String productName); 

    List<Product> findByCategory(Category category); 

    List<Product> findByPriceGreaterThanEqual(Integer price);  

    @Query("SELECT p FROM Product p WHERE p.productName LIKE %:name% AND p.category = :category")
    List<Product> findByProductNameAndCategory(@Param("name") String productName, @Param("category") Category category);

    @Query("SELECT p, s FROM Product p JOIN p.supplier s")  
    List<Product> findProductsWithOrders(); 
}
