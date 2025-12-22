package com.petshop.PetShop_management.dto;

import jakarta.validation.constraints.NotBlank;

public class CreateCustomerDTO {
    @NotBlank(message = "Firstname cannot be blank")
    private String firstname;

    @NotBlank(message = "Lastname cannot be blank")
    private String lastname;

    @NotBlank(message = "Surname cannot be blank")
    private String surname;

    @NotBlank(message = "Email cannot be blank")
    private String email;

    @NotBlank(message = "PhoneNumber cannot be blank")
    private String phoneNumber;

    @NotBlank(message = "Address cannot be blank")
    private String address;

    public CreateCustomerDTO() {}

    public CreateCustomerDTO(String firstname, String lastname, String surname,
                              String email, String phoneNumber, String address) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public String getFirstname() { return firstname; }
    public void setFirstname(String firstname) { this.firstname = firstname; }

    public String getLastname() { return lastname; }
    public void setLastname(String lastname) { this.lastname = lastname; }

    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
}

