package com.petshop.PetShop_management.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employee_id;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;

    @Column(nullable = true)
    private String surname;

    @Column(name = "job_title",nullable = false)
    private String jobTitle;

    @Column(name = "phone_number", unique = true, nullable = false)
    private String phoneNumber;

    @Column(nullable = true)
    private String address;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<ProductOrder> order;   

    public Employee() {}

    public Employee(String firstname, String lastname, String surname, 
        String jobTitle, String phoneNumber, String address) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.surname = surname;
        this.jobTitle = jobTitle;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public Long getEmployeeId() {return employee_id;}
    public void setEmployeeId(Long employee_id) {this.employee_id = employee_id;}

    public String getFirstName() {return firstname;}
    public void setFirstName(String firstname) {this.firstname = firstname;}

    public String getLastName() {return lastname;}
    public void setLastName(String lastname) {this.lastname = lastname;}

    public String getSurName() {return surname;}
    public void setSurName(String surname) {this.surname = surname;}

    public String getJobTitle() {return jobTitle;}
    public void setJobTitle(String jobTitle) {this.jobTitle = jobTitle;}

    public String getPhoneNum() {return phoneNumber;}
    public void setPhoneNum(String phoneNumber) {this.phoneNumber = phoneNumber;}

    public String getAddress() {return address;}
    public void setAddress(String address) {this.address = address;}
}
