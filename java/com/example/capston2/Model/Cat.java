package com.example.capston2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer catId;

    @Column(columnDefinition ="varchar(10) ")
    private String name;

    @Column(columnDefinition = "int not null")
    private Integer age;

    @NotEmpty(message = "Health status must not be empty!")
    @Pattern(regexp = "^(Healthy|Injured|Critical)$",
            message = "Health status must be one of the following: 'Healthy', 'Injured', or 'Critical'")
    @Column(columnDefinition = "varchar(10) not null")
    private String healthStatus;

    @NotEmpty(message = "location must not be Empty!...")
    @Column(columnDefinition ="varchar(200) not null")
    private String location;

    @Pattern(regexp = "^(Available|Adopted|Unavailable)$",message = "the adoption Status must be \"Available\" or \"Adopted\" or \"Unavailable\"")
    @Column(columnDefinition = "varchar(20) not null")
    private String adoptionStatus;

    @NotNull(message = " customerId cannot be null")
    @Column(columnDefinition = "int not null")
    private Integer customerId;

    public Cat(Integer catId, String name, Integer age, String healthStatus, String location, String adoptionStatus, Integer customerId) {
        this.catId = catId;
        this.name = name;
        this.age = age;
        this.healthStatus = healthStatus;
        this.location = location;
        this.adoptionStatus = adoptionStatus;
        this.customerId = customerId;
    }

    public Cat() {
    }

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(String healthStatus) {
        this.healthStatus = healthStatus;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAdoptionStatus() {
        return adoptionStatus;
    }

    public void setAdoptionStatus(String adoptionStatus) {
        this.adoptionStatus = adoptionStatus;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
}
