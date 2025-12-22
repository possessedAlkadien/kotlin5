package com.petshop.PetShop_management.controller;

import com.petshop.PetShop_management.dto.CustomerDTO;
import com.petshop.PetShop_management.dto.CreateCustomerDTO;
import com.petshop.PetShop_management.entity.Customer;
import com.petshop.PetShop_management.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        List<Customer> customers = customerService.findAll();
        List<CustomerDTO> customerDTOs = customers.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(customerDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id) {
        Optional<Customer> customer = customerService.findById(id);
        if (customer.isPresent()) {
            return ResponseEntity.ok(convertToDTO(customer.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@Valid @RequestBody CreateCustomerDTO createCustomerDTO) {
        try {
            Customer customer = convertToEntity(createCustomerDTO);
            Customer savedCustomer = customerService.save(customer);
            return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(savedCustomer));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long id, @Valid @RequestBody CreateCustomerDTO createCustomerDTO) {
        Optional<Customer> existingCustomer = customerService.findById(id);
        if (existingCustomer.isPresent()) {
            try {
                Customer customer = convertToEntity(createCustomerDTO);
                customer.setCustomerId(id);
                Customer updatedCustomer = customerService.save(customer);
                return ResponseEntity.ok(convertToDTO(updatedCustomer));
            } catch (Exception e) {
                return ResponseEntity.badRequest().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        Optional<Customer> customer = customerService.findById(id);
        if (customer.isPresent()) {
            customerService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<CustomerDTO>> searchCustomers(@RequestParam String lastname) {
        List<Customer> customers = customerService.findByFullName(lastname);
        List<CustomerDTO> customerDTOs = customers.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(customerDTOs);
    }

    private CustomerDTO convertToDTO(Customer customer) {
        CustomerDTO dto = new CustomerDTO();
        dto.setId(customer.getCustomerId());
        dto.setFirstname(customer.getFirstName());
        dto.setLastname(customer.getLastName());
        dto.setSurname(customer.getSurName());
        dto.setEmail(customer.getEmail());
        dto.setPhoneNumber(customer.getPhoneNum());
        dto.setAddress(customer.getAddress());
        return dto;
    }

    private Customer convertToEntity(CreateCustomerDTO dto) {
        Customer customer = new Customer();
        customer.setFirstName(dto.getFirstname());
        customer.setLastName(dto.getLastname());
        customer.setSurName(dto.getSurname());
        customer.setEmail(dto.getEmail());
        customer.setPhoneNum(dto.getPhoneNumber());
        customer.setAddress(dto.getAddress());
        return customer;
    }
}

