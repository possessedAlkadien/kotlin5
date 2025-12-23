package com.petshop.PetShop_management.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.petshop.PetShop_management.dto.CreateProductDTO;
import com.petshop.PetShop_management.entity.Product;
import com.petshop.PetShop_management.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureWebMvc
@Transactional
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProductRepository productRepository;  

    @BeforeEach
    void setUp() {
        productRepository.deleteAll();
    }

    @Test
    void getAllProducts_ShouldReturnEmptyList() throws Exception {
        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    void createProduct_ShouldReturnCreatedProduct() throws Exception {
        CreateProductDTO createProductDTO = new CreateProductDTO();
        createProductDTO.setName("Тестовый Продукт");
        createProductDTO.setDescription("Описание продукта");
        createProductDTO.setPrice(Integer.valueOf(99));
        createProductDTO.setQuantity(10);

        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createProductDTO)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.productName").value("Тестовый Продукт"))
                .andExpect(jsonPath("$.description").value("Описание продукта"))
                .andExpect(jsonPath("$.price").value(99))
                .andExpect(jsonPath("$.remnant").value(10));
    }

    @Test
    void getProductById_WhenProductExists_ShouldReturnProduct() throws Exception {
        Product product = new Product();
        product.setProductName("Продукт");
        product.setDescript("Описание");
        product.setPrice(Integer.valueOf(50));
        product.setRemnant(5);
        product = productRepository.save(product);

        mockMvc.perform(get("/api/products/{id}", product.getProductId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(product.getProductId()))
                .andExpect(jsonPath("$.productName").value("Продукт"))
                .andExpect(jsonPath("$.remnant").value(5));
    }

    @Test
    void getProductById_WhenProductNotExists_ShouldReturnNotFound() throws Exception {
        mockMvc.perform(get("/api/products/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void updateProduct_ShouldReturnUpdatedProduct() throws Exception {
        Product existingProduct = new Product();
        existingProduct.setProductName("Старый Продукт");
        existingProduct.setDescript("Старое описание");
        existingProduct.setPrice(Integer.valueOf(20));
        existingProduct.setRemnant(2);
        existingProduct = productRepository.save(existingProduct);

        CreateProductDTO updateDTO = new CreateProductDTO();
        updateDTO.setName("Обновленный Продукт");
        updateDTO.setDescription("Обновленное описание");
        updateDTO.setPrice(Integer.valueOf(150));
        updateDTO.setQuantity(15);

        mockMvc.perform(put("/api/products/{id}", existingProduct.getProductId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.productName").value("Обновленный Продукт"))
                .andExpect(jsonPath("$.price").value(150))
                .andExpect(jsonPath("$.remnant").value(15));
    }

    @Test
    void deleteProduct_ShouldReturnNoContent() throws Exception {
        Product product = new Product();
        product.setProductName("Продукт для удаления");
        product.setDescript("Описание");
        product.setPrice(Integer.valueOf(10));
        product.setRemnant(1);
        product = productRepository.save(product);

        mockMvc.perform(delete("/api/products/{id}", product.getProductId()))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/products/{id}", product.getProductId()))
                .andExpect(status().isNotFound());
    }

    @Test
    void searchProducts_ByName_ShouldReturnMatchingProducts() throws Exception {
        Product product1 = new Product();
        product1.setProductName("Кошкин корм");
        product1.setDescript("Вкусный корм");
        product1.setPrice(Integer.valueOf(30));
        product1.setRemnant(20);
        product1 = productRepository.save(product1);

        Product product2 = new Product();
        product2.setProductName("Собачий корм");
        product2.setDescript("Здоровый корм");
        product2.setPrice(Integer.valueOf(40));
        product2.setRemnant(15);
        product2 = productRepository.save(product2);

        Product product3 = new Product();
        product3.setProductName("Птичий корм");
        product3.setDescript("Сухой корм");
        product3.setPrice(Integer.valueOf(35));
        product3.setRemnant(10);
        product3 = productRepository.save(product3);

        mockMvc.perform(get("/api/products/search")
                        .param("name", "корм"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(3));

        mockMvc.perform(get("/api/products/search")
                        .param("name", "Кошкин"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].productName").value("Кошкин корм"));
    }
}

