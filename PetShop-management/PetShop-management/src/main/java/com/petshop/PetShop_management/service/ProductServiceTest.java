package com.petshop.PetShop_management.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.petshop.PetShop_management.entity.Product;
import com.petshop.PetShop_management.entity.Category;
import com.petshop.PetShop_management.repository.ProductRepository;
import com.petshop.PetShop_management.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;  

    private Product product1;
    private Product product2;
    private Category category;

    @BeforeEach
    void setUp() {
        category = new Category();
        category.setCategoryId(1L);
        category.setCategoryName("Food");

        product1 = new Product();
        product1.setProductId(1L);
        product1.setProductName("Dog Food");
        product1.setPrice(100);
        product1.setCategory(category);

        product2 = new Product();
        product2.setProductId(2L);
        product2.setProductName("Cat Food");
        product2.setPrice(200);
        product2.setCategory(category);
    }

    @Test
    void testFindAll() {
        List<Product> products = Arrays.asList(product1, product2);
        when(productRepository.findAll()).thenReturn(products);

        List<Product> result = productService.findAll();

        assertEquals(2, result.size());
        assertEquals(product1, result.get(0));
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product1));

        Optional<Product> result = productService.findById(1L);

        assertTrue(result.isPresent());
        assertEquals(product1, result.get());
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    void testSave() {
        when(productRepository.save(product1)).thenReturn(product1);

        Product result = productService.save(product1);

        assertEquals(product1, result);
        verify(productRepository, times(1)).save(product1);
    }

    @Test
    void testDeleteById() {
        doNothing().when(productRepository).deleteById(1L);

        productService.deleteById(1L);

        verify(productRepository, times(1)).deleteById(1L);
    }

    @Test
    void testFindByNameContaining() {
        List<Product> products = Arrays.asList(product1);
        when(productRepository.findByProductNameContainingIgnoreCase("Dog")).thenReturn(products);

        List<Product> result = productService.findByNameContaining("Dog");

        assertEquals(1, result.size());
        assertEquals(product1, result.get(0));
        verify(productRepository, times(1)).findByProductNameContainingIgnoreCase("Dog");
    }
}
