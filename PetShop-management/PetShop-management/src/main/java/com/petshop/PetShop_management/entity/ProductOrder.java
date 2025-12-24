package com.petshop.PetShop_management.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "productOrder")
public class ProductOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long order_id;

    @Column(name = "order_date", nullable = false)
    private LocalDate orderDate;

    @Column(name = "order_price", nullable = false)
    private Integer orderPrice;

    @Column(nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "customer")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "employee")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "supplier")
    private Supplier supplier;

    public ProductOrder() {}

    public ProductOrder(LocalDate orderDate, Integer orderPrice, String status) {
        this.orderDate = orderDate;
        this.orderPrice = orderPrice;
        this.status = status;
    }

    public Long getOrderId() {return order_id;}
    public void setOrderId(Long order_id) {this.order_id = order_id;}

    public LocalDate getOrderDate() {return orderDate;}
    public void setOrderDate(LocalDate orderDate) {this.orderDate = orderDate;}

    public Integer getOrderPrice() {return orderPrice;}
    public void setOrderPrice(Integer orderPrice) {this.orderPrice = orderPrice;}

    public String getStatus() {return status;}
    public void setStatus(String status) {this.status = status;}

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    public Supplier getSupplier() { return supplier; }
    public void setSupplier(Supplier supplier) { this.supplier = supplier; }

    public Employee getEmployee() { return employee; }
    public void setEmployee(Employee employee) { this.employee = employee; }

}
