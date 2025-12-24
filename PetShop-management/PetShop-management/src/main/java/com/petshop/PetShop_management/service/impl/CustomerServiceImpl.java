package com.petshop.PetShop_management.service.impl;

import com.petshop.PetShop_management.dto.CreateCustomerDTO;
import com.petshop.PetShop_management.entity.Customer;
import com.petshop.PetShop_management.repository.CustomerRepository;
import com.petshop.PetShop_management.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> findById(Long customer_id) {
        return customerRepository.findById(customer_id);
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void deleteById(Long customer_id) {
        customerRepository.deleteById(customer_id);
    }

     @Override
    public List<Customer> findByFullName(String fullName) {
        return customerRepository.findByLastnameContainingIgnoreCase(fullName);
    }

    public Customer createCustomerFromDTO(CreateCustomerDTO dto) {
        Customer customer = new Customer();
        customer.setFirstName(dto.getFirstname());
        customer.setLastName(dto.getLastname());
        customer.setEmail(dto.getEmail());
        customer.setPhoneNumber(dto.getPhoneNumber());
        return save(customer);
    }

}

