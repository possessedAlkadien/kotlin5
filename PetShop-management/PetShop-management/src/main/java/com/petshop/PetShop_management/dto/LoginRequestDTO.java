package com.petshop.PetShop_management.dto;

import jakarta.validation.constraints.NotBlank;

public class LoginRequestDTO {

    @NotBlank(message = "Email обязателен")
    private String email;
    @NotBlank(message = "Пароль обязателен")
    private String password;

    public LoginRequestDTO() {}

    public LoginRequestDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}


