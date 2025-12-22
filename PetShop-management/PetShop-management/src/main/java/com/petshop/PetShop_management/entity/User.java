package com.petshop.PetShop_management.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_cust")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;

    @Column(nullable = true)
    private String surname;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "phone_number", unique = true, nullable = false)
    private String phoneNumber;

    @Column(nullable = true)
    private String address;

    public User() {}

    public User(String firstname, String lastname, String surname, 
        String email, String phoneNumber, String address, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.password = password;
    }

    public Long getUserId() {return user_id;}
    public void setUserId(Long user_id) {this.user_id = user_id;}

    public String getFirstName() {return firstname;}
    public void setFirstName(String firstname) {this.firstname = firstname;}

    public String getLastName() {return lastname;}
    public void setLastName(String lastname) {this.lastname = lastname;}

    public String getSurName() {return surname;}
    public void setSurName(String surname) {this.surname = surname;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}

    public String getPhoneNum() {return phoneNumber;}
    public void setPhoneNum(String phoneNumber) {this.phoneNumber = phoneNumber;}

    public String getAddress() {return address;}
    public void setAddress(String address) {this.address = address;}
}
