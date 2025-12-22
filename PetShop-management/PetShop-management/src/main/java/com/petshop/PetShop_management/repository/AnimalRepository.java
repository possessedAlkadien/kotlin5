package com.petshop.PetShop_management.repository;

import com.petshop.PetShop_management.entity.Animal;
import com.petshop.PetShop_management.entity.Supplier;  

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
    List<Animal> findByNameContainingIgnoreCase(String name);  

    List<Animal> findByType(String type); 

    List<Animal> findByAgeGreaterThanEqual(Integer age); 

    List<Animal> findByGender(String gender);  

    List<Animal> findByPriceGreaterThanEqual(Integer price); 

    List<Animal> findBySupplier(Supplier supplier);  

    @Query("SELECT a FROM Animal a WHERE a.name LIKE %:name% AND a.type LIKE %:name%")
    List<Animal> findByNameAndType(@Param("name") String name, @Param("type") String type);  
}

