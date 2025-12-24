package com.petshop.PetShop_management.Service;

import com.petshop.PetShop_management.entity.Customer;
import com.petshop.PetShop_management.repository.CustomerRepository;
import com.petshop.PetShop_management.service.impl.CustomerServiceImpl; 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {
    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService; 

    private Customer testCustomer;

    @BeforeEach
    void setUp() {
        testCustomer = new Customer("Иван", "Иванов","Иванович", "ivan@example.com", "1234567890", "Невский проспект");
        testCustomer.setCustomerId(1L);
    }

    @Test
    void findAll_ShouldReturnAllCustomers() {
        // Given
        List<Customer> expectedCustomers = Arrays.asList(testCustomer);
        when(customerRepository.findAll()).thenReturn(expectedCustomers);

        // When
        List<Customer> actualCustomers = customerService.findAll();

        // Then
        assertEquals(1, actualCustomers.size());
        assertEquals("Иванов", actualCustomers.get(0).getLastName()); 
        verify(customerRepository).findAll();
    }

    @Test
    void findById_WhenCustomerExists_ShouldReturnCustomer() {
        // Given
        when(customerRepository.findById(1L)).thenReturn(Optional.of(testCustomer));

        // When
        Optional<Customer> result = customerService.findById(1L);

        // Then
        assertTrue(result.isPresent());
        assertEquals("Иванов", result.get().getLastName());
        verify(customerRepository).findById(1L);
    }

    @Test
    void findById_WhenCustomerNotExists_ShouldReturnEmpty() {
        // Given
        when(customerRepository.findById(999L)).thenReturn(Optional.empty());

        // When
        Optional<Customer> result = customerService.findById(999L);

        // Then
        assertFalse(result.isPresent());
        verify(customerRepository).findById(999L);
    }

    @Test
    void save_ShouldReturnSavedCustomer() {
        // Given
        when(customerRepository.save(any(Customer.class))).thenReturn(testCustomer);

        // When
        Customer result = customerService.save(testCustomer);

        // Then
        assertEquals("Иванов", result.getLastName());
        verify(customerRepository).save(testCustomer);
    }

    @Test
    void deleteById_ShouldCallRepository() {
        // Given
        doNothing().when(customerRepository).deleteById(1L);

        // When
        customerService.deleteById(1L);

        // Then
        verify(customerRepository).deleteById(1L);
    }

    @Test
    void findByFullName_ShouldReturnMatchingCustomers() {
        // Given
        List<Customer> expectedCustomers = Arrays.asList(testCustomer);
        when(customerRepository.findByLastnameContainingIgnoreCase(anyString())).thenReturn(expectedCustomers);

        List<Customer> result = customerService.findByFullName("Иванов");

        // Then
        assertEquals(1, result.size());
        assertEquals("Иванов", result.get(0).getLastName());
        verify(customerRepository).findByLastnameContainingIgnoreCase("Иванов");
    }
}
