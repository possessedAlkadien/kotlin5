package com.petshop.PetShop_management.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long category_id;

    @Column(name = "category_name", nullable = false)
    private String categoryName;

    @Column(nullable = true)
    private String description;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Product> product;   
    
    public Category() {}

    public Category(String categoryName, String description) {
        this.categoryName = categoryName;
        this.description = description;
    }

    public Long getCategoryId() {return category_id;}
    public void setCategoryId(Long category_id) {this.category_id = category_id;}

    public String getCategoryName() {return categoryName;}
    public void setCategoryName(String categoryName) {this.categoryName = categoryName;}

    public String getDescript() {return description;}
    public void setDescript(String description) {this.description = description;}

}
