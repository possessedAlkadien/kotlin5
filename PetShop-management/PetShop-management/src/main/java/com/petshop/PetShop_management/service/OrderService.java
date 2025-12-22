package com.petshop.PetShop_management.service;

import com.petshop.PetShop_management.entity.ProductOrder;
import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<ProductOrder> findAll();
    Optional<ProductOrder> findById(Long order_id);
    ProductOrder save(ProductOrder productOrder);
    void deleteById(Long order_id);
}
