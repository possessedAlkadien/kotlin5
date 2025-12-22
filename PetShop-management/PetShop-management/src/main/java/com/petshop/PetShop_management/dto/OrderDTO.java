package com.petshop.PetShop_management.dto;

import java.time.LocalDate;
import com.petshop.PetShop_management.entity.Customer;

public class OrderDTO {
    private Long id;
    private LocalDate orderDate;
    private Integer orderPrice;
    private String status;
    private Customer customerId;
    private String customerName; 
    private Long employeeId;
    private String employeeName;
    private Long supplierId;
    private String supplierName;

    public OrderDTO() {}

    public OrderDTO(Long id, LocalDate orderDate, Integer orderPrice, String status,
                    Customer customerId, String customerName, Long employeeId, String employeeName,
                    Long supplierId, String supplierName) {
        this.id = id;
        this.orderDate = orderDate;
        this.orderPrice = orderPrice;
        this.status = status;
        this.customerId = customerId;
        this.customerName = customerName;
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.supplierId = supplierId;
        this.supplierName = supplierName;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDate orderDate) { this.orderDate = orderDate; }

    public Integer getOrderPrice() { return orderPrice; }
    public void setOrderPrice(Integer orderPrice) { this.orderPrice = orderPrice; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Customer getCustomerId() { return customerId; }
    public void setCustomerId(Customer customerId) { this.customerId = customerId; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public Long getEmployeeId() { return employeeId; }
    public void setEmployeeId(Long employeeId) { this.employeeId = employeeId; }

    public String getEmployeeName() { return employeeName; }
    public void setEmployeeName(String employeeName) { this.employeeName = employeeName; }

    public Long getSupplierId() { return supplierId; }
    public void setSupplierId(Long supplierId) { this.supplierId = supplierId; }

    public String getSupplierName() { return supplierName; }
    public void setSupplierName(String supplierName) { this.supplierName = supplierName; }
}
