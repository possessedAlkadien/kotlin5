package com.petshop.PetShop_management.service.impl;

import com.petshop.PetShop_management.dto.CreateOrderDTO;
import com.petshop.PetShop_management.entity.Customer;
import com.petshop.PetShop_management.entity.Product;
import com.petshop.PetShop_management.entity.ProductOrder;
import com.petshop.PetShop_management.repository.ProductOrderRepository;
import com.petshop.PetShop_management.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductOrderRepository productOrderRepository;

    @Override
    public List<ProductOrder> findAll() {
        return productOrderRepository.findAll();
    }

    @Override
    public Optional<ProductOrder> findById(Long order_id) {
        return productOrderRepository.findById(order_id);
    }

    @Override
    public ProductOrder save(ProductOrder productOrder) {
        return productOrderRepository.save(productOrder);
    }

    @Override
    public void deleteById(Long order_id) {
        productOrderRepository.deleteById(order_id);
    }

    public ProductOrder createOrderWithCustomerAndProducts(CreateOrderDTO dto, Customer customer, List<Product> products) {
        ProductOrder order = new ProductOrder();
        order.setOrderDate(dto.getOrderDate());
        order.setOrderPrice(dto.getOrderPrice());
        order.setStatus(dto.getStatus() != null ? dto.getStatus() : "Новый");
        order.setCustomer(customer);
        return save(order);
    }

}

