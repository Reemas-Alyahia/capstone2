package com.example.capston2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;

    @NotEmpty(message = "Name must not be Empty!...")
    @Column(columnDefinition ="varchar(100) not null")
    private String name;

    @NotNull(message = "price cannot be null")
    @Column(columnDefinition ="int not null")
    private Integer price;

    @NotNull(message = "stock cannot be null")
    @Column(columnDefinition ="int not null")
    private Integer stock;

    @NotNull(message = "for age cannot be null")
    @Column(columnDefinition ="int not null")
    private Integer ageFor;

    @NotEmpty(message = "description must not be Empty!...")
    @Column(columnDefinition ="varchar(200) not null")
    private String description;

    @NotEmpty(message = "category must not be Empty!...")
    @Pattern(regexp = "^(Food|Healthcare|Toys|Accessories)$")
    @Column(columnDefinition ="varchar(50) not null")
    private String category;

    @Column(columnDefinition ="double default 0")
    private Double discount;

    public Product(Integer productId, String name, Integer price, Integer stock, Integer ageFor, String description, String category, Double discount) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.ageFor = ageFor;
        this.description = description;
        this.category = category;
        this.discount = discount;
    }

    public Product() {
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getAgeFor() {
        return ageFor;
    }

    public void setAgeFor(Integer ageFor) {
        this.ageFor = ageFor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }
}
