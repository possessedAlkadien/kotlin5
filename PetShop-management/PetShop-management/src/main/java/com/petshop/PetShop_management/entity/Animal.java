package com.petshop.PetShop_management.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "animal")
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long animal_id;
    
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "supplier")
    private Supplier supplier;

    public Animal() {}

    public Animal (String name, String type, Integer age, String gender, Integer price, String status) {
        this.name = name;
        this.type = type;
        this.age = age;
        this.gender = gender;
        this.price = price;
        this.status = status;
    }

    public Long getAnimalId() {return animal_id;}
    public void setAnimalId(Long animal_id) {this.animal_id = animal_id;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getType() {return type;}
    public void setType(String type) {this.type = type;}

    public Integer getAge() {return age;}
    public void setAge(Integer age) {this.age = age;}

    public String getGender() {return gender;}
    public void setGender(String gender) {this.gender = gender;}

    public Integer getPrice() {return price;}
    public void setPrice(Integer price) {this.price = price;}

    public String getStatus() {return status;}
    public void setStatus(String status) {this.status = status;}

    public Supplier getSupplier() { return supplier; }
    public void setSupplier(Supplier supplier) { this.supplier = supplier; }
}
