package com.petshop.PetShop_management.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.petshop.PetShop_management.dto.CreateOrderDTO;
import com.petshop.PetShop_management.dto.OrderDTO;
import com.petshop.PetShop_management.entity.Customer;
import com.petshop.PetShop_management.entity.ProductOrder;
import com.petshop.PetShop_management.repository.CustomerRepository;
import com.petshop.PetShop_management.repository.ProductOrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootTest
@AutoConfigureWebMvc
@Transactional
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProductOrderRepository orderRepository; 

    @Autowired
    private CustomerRepository customerRepository; 

    @BeforeEach
    void setUp() {
        orderRepository.deleteAll();
        customerRepository.deleteAll();
    }

    @Test
    void getAllOrders_ShouldReturnEmptyList() throws Exception {
        mockMvc.perform(get("/api/orders"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    void createOrder_ShouldReturnCreatedOrder() throws Exception {
        Customer customer = new Customer();
        customer.setFirstName("Иван");
        customer.setLastName("Иванов");
        customer.setSurName("Иванович");
        customer.setEmail("ivan@example.com");
        customer.setPhoneNum("+1234567890");
        customer.setAddress("Москва, ул. Ленина");
        customer = customerRepository.save(customer);

        CreateOrderDTO createOrderDTO = new CreateOrderDTO();
        createOrderDTO.setOrderDate(LocalDate.now());
        createOrderDTO.setOrderPrice(Integer.valueOf(100));
        createOrderDTO.setStatus("PENDING");

        mockMvc.perform(post("/api/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createOrderDTO)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.orderDate").value(LocalDate.now().toString()))
                .andExpect(jsonPath("$.orderPrice").value(100.00))
                .andExpect(jsonPath("$.status").value("PENDING"));
    }

    @Test
    void getOrderById_WhenOrderExists_ShouldReturnOrder() throws Exception {
        Customer customer = new Customer();
        customer.setFirstName("Иван");
        customer.setLastName("Иванов");
        customer.setSurName("Иванович");
        customer.setEmail("ivan@example.com");
        customer.setPhoneNum("+1234567890");
        customer.setAddress("Москва, ул. Ленина");
        customer = customerRepository.save(customer);

        ProductOrder order = new ProductOrder();
        order.setCustomer(customer); 
        order.setOrderDate(LocalDate.now());
        order.setOrderPrice(Integer.valueOf(100));
        order.setStatus("PENDING");
        order = orderRepository.save(order);

        mockMvc.perform(get("/api/orders/{id}", order.getOrderId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(order.getOrderId()))
                .andExpect(jsonPath("$.orderDate").value(LocalDate.now().toString()))
                .andExpect(jsonPath("$.orderPrice").value(100.00));
    }

    @Test
    void getOrderById_WhenOrderNotExists_ShouldReturnNotFound() throws Exception {
        mockMvc.perform(get("/api/orders/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void updateOrder_ShouldReturnUpdatedOrder() throws Exception {
        Customer customer = new Customer();
        customer.setFirstName("Иван");
        customer.setLastName("Иванов");
        customer.setSurName("Иванович");
        customer.setEmail("ivan@example.com");
        customer.setPhoneNum("+1234567890");
        customer.setAddress("Москва, ул. Ленина");
        customer = customerRepository.save(customer);

        ProductOrder existingOrder = new ProductOrder();
        existingOrder.setCustomer(customer);
        existingOrder.setOrderDate(LocalDate.now().minusDays(1));
        existingOrder.setOrderPrice(Integer.valueOf(50));
        existingOrder.setStatus("PENDING");
        existingOrder = orderRepository.save(existingOrder);

        CreateOrderDTO updateDTO = new CreateOrderDTO();
        updateDTO.setOrderDate(LocalDate.now());
        updateDTO.setOrderPrice(Integer.valueOf(150));
        updateDTO.setStatus("COMPLETED");

        mockMvc.perform(put("/api/orders/{id}", existingOrder.getOrderId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.orderPrice").value(150.00))
                .andExpect(jsonPath("$.status").value("COMPLETED"));
    }

    @Test
    void deleteOrder_ShouldReturnNoContent() throws Exception {
        Customer customer = new Customer();
        customer.setFirstName("Иван");
        customer.setLastName("Иванов");
        customer.setSurName("Иванович");
        customer.setEmail("ivan@example.com");
        customer.setPhoneNum("+1234567890");
        customer.setAddress("Москва, ул. Ленина");
        customer = customerRepository.save(customer);

        ProductOrder order = new ProductOrder();
        order.setCustomer(customer);
        order.setOrderDate(LocalDate.now());
        order.setOrderPrice(Integer.valueOf(100));
        order.setStatus("PENDING");
        order = orderRepository.save(order);

        mockMvc.perform(delete("/api/orders/{id}", order.getOrderId()))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/orders/{id}", order.getOrderId()))
                .andExpect(status().isNotFound());
    }
}
