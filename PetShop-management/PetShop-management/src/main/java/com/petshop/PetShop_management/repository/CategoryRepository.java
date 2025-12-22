package com.petshop.PetShop_management.repository;

import com.petshop.PetShop_management.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByCategoryNameContainingIgnoreCase(String categoryName);

    List<Category> findByDescriptionContainingIgnoreCase(String description);

    @Query("SELECT c FROM Category c WHERE LOWER(c.categoryName) LIKE LOWER(CONCAT('%', :categoryName, '%')) AND LOWER(c.description) LIKE LOWER(CONCAT('%', :description, '%'))")
    List<Category> findByCategoryNameAndDescription(@Param("categoryName") String categoryName, @Param("description") String description);

    @Query("SELECT DISTINCT c FROM Category c JOIN c.product p")
    List<Category> findCategoriesWithProducts(); 

}
