package com.petshop.PetShop_management.repository;

import com.petshop.PetShop_management.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByLastnameContainingIgnoreCase(String lastname);

    Optional<Customer> findByEmail(String email);

    Optional<Customer> findById(Long customer_id);

    List<Customer> findByPhoneNumber(String phoneNumber);

    @Query("SELECT c FROM Customer c WHERE c.lastname LIKE %:lastname%")
    List<Customer> findByFullName(@Param("lastname") String lastname);

    @Query("SELECT DISTINCT c FROM Customer c JOIN c.order po")
    List<Customer> findCustomersWithOrders();
}
