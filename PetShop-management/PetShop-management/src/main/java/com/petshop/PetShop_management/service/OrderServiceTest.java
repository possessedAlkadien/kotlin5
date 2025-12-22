package com.petshop.PetShop_management.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.petshop.PetShop_management.entity.ProductOrder;
import com.petshop.PetShop_management.repository.ProductOrderRepository;
import com.petshop.PetShop_management.service.impl.OrderServiceImpl;
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
class OrderServiceTest {

    @Mock
    private ProductOrderRepository productOrderRepository;

    @InjectMocks
    private OrderServiceImpl orderService;  

    private ProductOrder order1;
    private ProductOrder order2;

    @BeforeEach
    void setUp() {
        order1 = new ProductOrder();
        order1.setOrderId(1L);
        order1.setOrderPrice(500);
        order1.setStatus("Новый");

        order2 = new ProductOrder();
        order2.setOrderId(2L);
        order2.setOrderPrice(700);
        order2.setStatus("Выполнен");
    }

    @Test
    void testFindAll() {
        List<ProductOrder> orders = Arrays.asList(order1, order2);
        when(productOrderRepository.findAll()).thenReturn(orders);

        List<ProductOrder> result = orderService.findAll();

        assertEquals(2, result.size());
        assertEquals(order1, result.get(0));
        verify(productOrderRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        when(productOrderRepository.findById(1L)).thenReturn(Optional.of(order1));

        Optional<ProductOrder> result = orderService.findById(1L);

        assertTrue(result.isPresent());
        assertEquals(order1, result.get());
        verify(productOrderRepository, times(1)).findById(1L);
    }

    @Test
    void testSave() {
        when(productOrderRepository.save(order1)).thenReturn(order1);

        ProductOrder result = orderService.save(order1);

        assertEquals(order1, result);
        verify(productOrderRepository, times(1)).save(order1);
    }

    @Test
    void testDeleteById() {
        doNothing().when(productOrderRepository).deleteById(1L);

        orderService.deleteById(1L);

        verify(productOrderRepository, times(1)).deleteById(1L);
    }
}
