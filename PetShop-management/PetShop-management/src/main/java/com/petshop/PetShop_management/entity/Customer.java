package com.petshop.PetShop_management.entity;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customer_id;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;

    @Column(nullable = true)
    private String surname;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(name = "phone_number", unique = true, nullable = false)
    private String phoneNumber;

    @Column(nullable = true)
    private String address;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<ProductOrder> order;   

    public Customer() {}

    public Customer(String firstname, String lastname, String surname, 
        String email, String phoneNumber, String address) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public Long getCustomerId() {return customer_id;}
    public void setCustomerId(Long customer_id) {this.customer_id = customer_id;}

    public String getFirstName() {return firstname;}
    public void setFirstName(String firstname) {this.firstname = firstname;}

    public String getLastName() {return lastname;}
    public void setLastName(String lastname) {this.lastname = lastname;}

    public String getSurName() {return surname;}
    public void setSurName(String surname) {this.surname = surname;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public String getPhoneNumber() {return phoneNumber;}
    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}

    public String getAddress() {return address;}
    public void setAddress(String address) {this.address = address;}
}
