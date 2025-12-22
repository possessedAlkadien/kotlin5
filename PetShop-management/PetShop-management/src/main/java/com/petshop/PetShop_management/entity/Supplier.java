package com.petshop.PetShop_management.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "supplier")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long supplier_id;

    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String address;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
    private List<Product> product;   

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
    private List<ProductOrder> order;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
    private List<Animal> animal;
    
    public Supplier() {}

    public Supplier(String companyName, String phoneNumber, String address) {
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public Long getSupplierId() {return supplier_id;}
    public void setSupplierId(Long supplier_id) {this.supplier_id = supplier_id;}

    public String getCompanyName() {return companyName;}
    public void setCompanyName(String companyName) {this.companyName = companyName;}

    public String getPhoneNumber() {return phoneNumber;}
    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}

    public String getAddress() {return address;}
    public void setAddress(String address) {this.address = address;}
}
