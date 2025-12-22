package com.petshop.PetShop_management.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.petshop.PetShop_management.dto.CreateCustomerDTO;
import com.petshop.PetShop_management.dto.CustomerDTO;
import com.petshop.PetShop_management.entity.Customer;
import com.petshop.PetShop_management.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureWebMvc
@Transactional
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CustomerRepository customerRepository;  

    @BeforeEach
    void setUp() {
        customerRepository.deleteAll();
    }

    @Test
    void getAllCustomers_ShouldReturnEmptyList() throws Exception {
        mockMvc.perform(get("/api/customers"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    void createCustomer_ShouldReturnCreatedCustomer() throws Exception {
        CreateCustomerDTO createCustomerDTO = new CreateCustomerDTO();
        createCustomerDTO.setFirstname("Иван");
        createCustomerDTO.setLastname("Иванов");
        createCustomerDTO.setSurname("Иванович");
        createCustomerDTO.setEmail("ivan@example.com");
        createCustomerDTO.setPhoneNumber("+1234567890");
        createCustomerDTO.setAddress("Москва, ул. Ленина");

        mockMvc.perform(post("/api/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createCustomerDTO)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstname").value("Иван"))
                .andExpect(jsonPath("$.lastname").value("Иванов"))
                .andExpect(jsonPath("$.email").value("ivan@example.com"));
    }

    @Test
    void getCustomerById_WhenCustomerExists_ShouldReturnCustomer() throws Exception {
        Customer customer = new Customer();
        customer.setFirstName("Иван");
        customer.setLastName("Иванов");
        customer.setSurName("Иванович");
        customer.setEmail("ivan@example.com");
        customer.setPhoneNum("+1234567890");
        customer.setAddress("Москва, ул. Ленина");
        customer = customerRepository.save(customer);

        mockMvc.perform(get("/api/customers/{id}", customer.getCustomerId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(customer.getCustomerId()))
                .andExpect(jsonPath("$.firstname").value("Иван"));
    }

    @Test
    void getCustomerById_WhenCustomerNotExists_ShouldReturnNotFound() throws Exception {
        mockMvc.perform(get("/api/customers/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void updateCustomer_ShouldReturnUpdatedCustomer() throws Exception {
        Customer existingCustomer = new Customer();
        existingCustomer.setFirstName("Иван");
        existingCustomer.setLastName("Иванов");
        existingCustomer.setSurName("Иванович");
        existingCustomer.setEmail("ivan@example.com");
        existingCustomer.setPhoneNum("+1234567890");
        existingCustomer.setAddress("Москва, ул. Ленина");
        existingCustomer = customerRepository.save(existingCustomer);

        CreateCustomerDTO updateDTO = new CreateCustomerDTO();
        updateDTO.setFirstname("Петр");
        updateDTO.setLastname("Петров");
        updateDTO.setSurname("Петрович");
        updateDTO.setEmail("petr@example.com");
        updateDTO.setPhoneNumber("+0987654321");
        updateDTO.setAddress("СПб, ул. Пушкина");

        mockMvc.perform(put("/api/customers/{id}", existingCustomer.getCustomerId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstname").value("Петр"))
                .andExpect(jsonPath("$.lastname").value("Петров"));
    }

    @Test
    void deleteCustomer_ShouldReturnNoContent() throws Exception {
        Customer customer = new Customer();
        customer.setFirstName("Иван");
        customer.setLastName("Иванов");
        customer.setSurName("Иванович");
        customer.setEmail("ivan@example.com");
        customer.setPhoneNum("+1234567890");
        customer.setAddress("Москва, ул. Ленина");
        customer = customerRepository.save(customer);

        mockMvc.perform(delete("/api/customers/{id}", customer.getCustomerId()))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/customers/{id}", customer.getCustomerId()))
                .andExpect(status().isNotFound());
    }

    @Test
    void searchCustomers_ShouldReturnMatchingCustomers() throws Exception {
        Customer customer = new Customer();
        customer.setFirstName("Иван");
        customer.setLastName("Иванов");
        customer.setSurName("Иванович");
        customer.setEmail("ivan@example.com");
        customer.setPhoneNum("+1234567890");
        customer.setAddress("Москва, ул. Ленина");
        customerRepository.save(customer);

        mockMvc.perform(get("/api/customers/search")
                        .param("lastname", "Иванов"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].lastname").value("Иванов"));
    }
}
