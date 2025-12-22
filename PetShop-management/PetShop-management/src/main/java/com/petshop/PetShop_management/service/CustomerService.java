package com.petshop.PetShop_management.service;

import com.petshop.PetShop_management.entity.Customer;
import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<Customer> findAll();
    Optional<Customer> findById(Long customer_id);
    Customer save(Customer customer);
    void deleteById(Long customer_id);
    List<Customer> findByFullName(String lastname);
}
