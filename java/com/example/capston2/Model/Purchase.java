package com.example.capston2.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Purchase {

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Integer purchaseId;

    @NotNull(message = "product id cannot be null")
    @Column(columnDefinition = "int not null")
    private Integer productId;

    @NotNull(message = "Customer id cannot be null")
    @Column(columnDefinition = "int not null")
    private Integer customerId;


    @NotNull(message = "quantity cannot be null")
    @Min(value = 1,message = "quantity must be at least 1 ")
    @Column(columnDefinition = "int not null")
    private Integer quantity;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(columnDefinition = "DATE")
    private LocalDate purchaseDate;

    public Purchase(Integer purchaseId, Integer productId, Integer customerId, Integer quantity, LocalDate purchaseDate) {
        this.purchaseId = purchaseId;
        this.productId = productId;
        this.customerId = customerId;
        this.quantity = quantity;
        this.purchaseDate = purchaseDate;
    }

    public Purchase() {
    }

    public Integer getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Integer purchaseId) {
        this.purchaseId = purchaseId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
}
