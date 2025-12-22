package com.petshop.PetShop_management.repository;

import com.petshop.PetShop_management.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {  

    List<Supplier> findByCompanyName(String companyName);  
    List<Supplier> findByPhoneNumber(String phoneNumber);  
    List<Supplier> findByAddress(String address); 

    @Query("SELECT DISTINCT s FROM Supplier s " +
           "JOIN FETCH s.product p " +
           "WHERE s.companyName LIKE %:companyNamePart%")
    List<Supplier> findSuppliersWithProductsByCompanyName(@Param("companyNamePart") String companyNamePart);

    @Query("SELECT DISTINCT s FROM Supplier s " +
           "JOIN FETCH s.order po " +
           "WHERE s.address LIKE %:addressPart%")
    List<Supplier> findSuppliersWithOrdersByAddress(@Param("addressPart") String addressPart);

    @Query("SELECT COUNT(p) FROM Product p WHERE p.supplier.supplier_id = :supplierId")
    Long countProductsForSupplier(@Param("supplierId") Long supplierId);

    @Query("SELECT COUNT(po) FROM ProductOrder po WHERE po.supplier.supplier_id = :supplierId")
    Long countOrdersForSupplier(@Param("supplierId") Long supplierId);

    @Query("SELECT COUNT(a) FROM Animal a WHERE a.supplier.supplier_id = :supplierId")
    Long countAnimalsForSupplier(@Param("supplierId") Long supplierId);
}

