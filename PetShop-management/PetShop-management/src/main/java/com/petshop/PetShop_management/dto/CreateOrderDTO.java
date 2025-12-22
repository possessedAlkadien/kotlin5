package com.petshop.PetShop_management.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;

public class CreateOrderDTO {
    @NotNull(message = "Дата заказа обязательна")
    private LocalDate orderDate;

    @NotNull(message = "Цена заказа обязательна")
    @Positive(message = "Цена заказа должна быть положительной")
    private Integer orderPrice;

    @NotBlank(message = "Статус обязателен")
    private String status;

    @NotNull(message = "ID клиента обязателен")
    private Long customerId;

    @NotNull(message = "ID сотрудника обязателен")
    private Long employeeId;

    @NotNull(message = "ID поставщика обязателен")
    private Long supplierId;

    public CreateOrderDTO() {}

    public LocalDate getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDate orderDate) { this.orderDate = orderDate; }

    public Integer getOrderPrice() { return orderPrice; }
    public void setOrderPrice(Integer orderPrice) { this.orderPrice = orderPrice; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }

    public Long getEmployeeId() { return employeeId; }
    public void setEmployeeId(Long employeeId) { this.employeeId = employeeId; }

    public Long getSupplierId() { return supplierId; }
    public void setSupplierId(Long supplierId) { this.supplierId = supplierId; }
}

