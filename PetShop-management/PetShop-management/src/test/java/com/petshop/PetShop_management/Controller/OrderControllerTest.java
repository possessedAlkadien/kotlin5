package com.petshop.PetShop_management.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.petshop.PetShop_management.dto.CreateOrderDTO;
import com.petshop.PetShop_management.entity.*;
import com.petshop.PetShop_management.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc; 
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.time.LocalDate;

@SpringBootTest
@AutoConfigureWebMvc
@AutoConfigureMockMvc
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

    @Autowired
    private EmployeeRepository employeeRepository; 

    @Autowired
    private SupplierRepository supplierRepository; 

    @BeforeEach
    void setUp() {
        orderRepository.deleteAll();
        customerRepository.deleteAll();
    }

    @Test
    @WithMockUser
    void getAllOrders_ShouldReturnEmptyList() throws Exception {
        mockMvc.perform(get("/api/orders"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    @WithMockUser
    void createOrder_ShouldReturnCreatedOrder() throws Exception {
        Customer customer = new Customer();
        customer.setFirstName("Иван");
        customer.setLastName("Иванов");
        customer.setSurName("Иванович");
        customer.setEmail("ivan@example.com");
        customer.setPhoneNumber("+1234567890");
        customer.setAddress("Москва, ул. Ленина");
        customer = customerRepository.save(customer);

        Employee employee = new Employee();  
        employee.setFirstName("Петр");
        employee.setLastName("Петров");
        employee.setSurName("Петрович");
        employee.setPhoneNum("+0987654321");
        employee.setAddress("Москва, ул. Тверская");
        employee.setJobTitle("Менеджер"); 
        employee = employeeRepository.save(employee);  

        Supplier supplier = new Supplier();  
        supplier.setCompanyName("ООО Супп");  
        supplier.setPhoneNumber("+1122334455");
        supplier.setAddress("СПб, ул. Невская");
        supplier = supplierRepository.save(supplier); 

        CreateOrderDTO createOrderDTO = new CreateOrderDTO();
        createOrderDTO.setOrderDate(LocalDate.now());
        createOrderDTO.setOrderPrice(Integer.valueOf(100)); 
        createOrderDTO.setStatus("PENDING");
        createOrderDTO.setCustomerId(customer.getCustomerId());  
        createOrderDTO.setEmployeeId(employee.getEmployeeId());  
        createOrderDTO.setSupplierId(supplier.getSupplierId());  

        mockMvc.perform(post("/api/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createOrderDTO)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.orderDate").value(LocalDate.now().toString()))
                .andExpect(jsonPath("$.orderPrice").value(100)) 
                .andExpect(jsonPath("$.status").value("PENDING"));
    }


    @Test
    @WithMockUser
    void getOrderById_WhenOrderExists_ShouldReturnOrder() throws Exception {
        Customer customer = new Customer();
        customer.setFirstName("Иван");
        customer.setLastName("Иванов");
        customer.setSurName("Иванович");
        customer.setEmail("ivan@example.com");
        customer.setPhoneNumber("+1234567890");
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
    @WithMockUser
    void getOrderById_WhenOrderNotExists_ShouldReturnNotFound() throws Exception {
        mockMvc.perform(get("/api/orders/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser
    void updateOrder_ShouldReturnUpdatedOrder() throws Exception {
        Customer customer = new Customer();
        customer.setFirstName("Иван");
        customer.setLastName("Иванов");
        customer.setSurName("Иванович");
        customer.setEmail("ivan@example.com");
        customer.setPhoneNumber("+1234567890");
        customer.setAddress("Москва, ул. Ленина");
        customer = customerRepository.save(customer);

        Employee employee = new Employee();  
        employee.setFirstName("Петр");
        employee.setLastName("Петров");
        employee.setSurName("Петрович");
        employee.setPhoneNum("+0987654321");
        employee.setAddress("Москва, ул. Тверская");
        employee.setJobTitle("Менеджер"); 
        employee = employeeRepository.save(employee);  

        Supplier supplier = new Supplier();  
        supplier.setCompanyName("ООО Супп");  
        supplier.setPhoneNumber("+1122334455");
        supplier.setAddress("СПб, ул. Невская");
        supplier = supplierRepository.save(supplier); 

        ProductOrder existingOrder = new ProductOrder();
        existingOrder.setCustomer(customer);
        existingOrder.setEmployee(employee);  
        existingOrder.setSupplier(supplier);  
        existingOrder.setOrderDate(LocalDate.now().minusDays(1));
        existingOrder.setOrderPrice(Integer.valueOf(50));
        existingOrder.setStatus("PENDING");
        existingOrder = orderRepository.save(existingOrder);

        CreateOrderDTO updateDTO = new CreateOrderDTO();
        updateDTO.setCustomerId(customer.getCustomerId());  
        updateDTO.setEmployeeId(employee.getEmployeeId());  
        updateDTO.setSupplierId(supplier.getSupplierId());  
        updateDTO.setOrderDate(LocalDate.now());
        updateDTO.setOrderPrice(Integer.valueOf(150));
        updateDTO.setStatus("COMPLETED");

        mockMvc.perform(put("/api/orders/{id}", existingOrder.getOrderId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.orderPrice").value(150))
                .andExpect(jsonPath("$.status").value("COMPLETED"));
    }

    @Test
    @WithMockUser
    void deleteOrder_ShouldReturnNoContent() throws Exception {
        Customer customer = new Customer();
        customer.setFirstName("Иван");
        customer.setLastName("Иванов");
        customer.setSurName("Иванович");
        customer.setEmail("ivan@example.com");
        customer.setPhoneNumber("+1234567890");
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
