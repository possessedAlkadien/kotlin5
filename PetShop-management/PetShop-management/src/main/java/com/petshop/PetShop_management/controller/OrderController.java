package com.petshop.PetShop_management.controller;

import com.petshop.PetShop_management.dto.OrderDTO;
import com.petshop.PetShop_management.dto.CreateOrderDTO;
import com.petshop.PetShop_management.entity.ProductOrder;
import com.petshop.PetShop_management.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        List<ProductOrder> orders = orderService.findAll();
        List<OrderDTO> orderDTOs = orders.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(orderDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id) {
        Optional<ProductOrder> order = orderService.findById(id);
        if (order.isPresent()) {
            return ResponseEntity.ok(convertToDTO(order.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@Valid @RequestBody CreateOrderDTO createOrderDTO) {
        try {
            ProductOrder order = convertToEntity(createOrderDTO);
            ProductOrder savedOrder = orderService.save(order);
            return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(savedOrder));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable Long id, @Valid @RequestBody CreateOrderDTO createOrderDTO) {
        Optional<ProductOrder> existingOrder = orderService.findById(id);
        if (existingOrder.isPresent()) {
            try {
                ProductOrder order = convertToEntity(createOrderDTO);
                order.setOrderId(id);
                ProductOrder updatedOrder = orderService.save(order);
                return ResponseEntity.ok(convertToDTO(updatedOrder));
            } catch (Exception e) {
                return ResponseEntity.badRequest().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        Optional<ProductOrder> order = orderService.findById(id);
        if (order.isPresent()) {
            orderService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private OrderDTO convertToDTO(ProductOrder order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getOrderId());
        dto.setCustomerId(order.getCustomer());
        dto.setOrderDate(order.getOrderDate());
        dto.setOrderPrice(order.getOrderPrice());
        dto.setStatus(order.getStatus());
        return dto;
    }

    private ProductOrder convertToEntity(CreateOrderDTO dto) {
        ProductOrder order = new ProductOrder();
        order.setOrderDate(dto.getOrderDate());
        order.setOrderPrice(dto.getOrderPrice());
        order.setStatus(dto.getStatus());
        return order;
    }
}

