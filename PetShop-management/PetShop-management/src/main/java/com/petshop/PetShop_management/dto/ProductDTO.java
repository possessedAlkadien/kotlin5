package com.petshop.PetShop_management.dto;

public class ProductDTO {
    private Long id;
    private String productName;
    private String description;
    private Integer price;
    private Integer remnant;
    private Long categoryId;
    private String categoryName;
    private Long supplierId;
    private String supplierName;

    public ProductDTO() {}

    public ProductDTO(Long id, String productName, String description, Integer price,
                      Integer remnant, Long categoryId, String categoryName,
                      Long supplierId, String supplierName) {
        this.id = id;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.remnant = remnant;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.supplierId = supplierId;
        this.supplierName = supplierName;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Integer getPrice() { return price; }
    public void setPrice(Integer price) { this.price = price; }

    public Integer getRemnant() { return remnant; }
    public void setRemnant(Integer remnant) { this.remnant = remnant; }

    public Long getCategoryId() { return categoryId; }
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }

    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }

    public Long getSupplierId() { return supplierId; }
    public void setSupplierId(Long supplierId) { this.supplierId = supplierId; }

    public String getSupplierName() { return supplierName; }
    public void setSupplierName(String supplierName) { this.supplierName = supplierName; }
}

