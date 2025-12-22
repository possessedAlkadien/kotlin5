package com.petshop.PetShop_management.dto;

public class CustomerDTO {
    private Long id;
    private String firstname;
    private String lastname;
    private String surname;
    private String email;
    private String phoneNumber;
    private String address;

    public CustomerDTO() {}

    public CustomerDTO(Long id, String firstname, String lastname, String surname,
                        String email, String phoneNumber, String address) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

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

