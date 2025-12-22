package com.petshop.PetShop_management.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long product_id;

    @Column(name = "product_name", nullable = false, unique = true)
    private String productName;

    @Column(nullable = true)
    private String description;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private Integer remnant;

    @ManyToOne
    @JoinColumn(name = "category")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "supplier")
    private Supplier supplier;  

    public Product() {}

    public Product(String productName, String description, Integer price,
    Integer remnant, Category category, Supplier supplier) {
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.remnant = remnant; 
        this.category = category;
        this.supplier = supplier;
    }

    public Long getProductId() {return product_id;}
    public void setProductId(Long product_id) {this.product_id = product_id;}

    public String getProductName() {return productName;}
    public void setProductName(String productName) {this.productName = productName;}

    public String getDescript() {return description;}
    public void setDescript(String description) {this.description = description;}

    public Integer getPrice() {return price;}
    public void setPrice(Integer price) {this.price = price;}

    public Integer getRemnant() {return remnant;}
    public void setRemnant(Integer remnant) {this.remnant = remnant;}

    public Category getCategory() {return category;}
    public void setCategory(Category category) {this.category = category;}

    public Supplier getSupplier() {return supplier;}
    public void setSupplier(Supplier supplier) {this.supplier = supplier;}
}
