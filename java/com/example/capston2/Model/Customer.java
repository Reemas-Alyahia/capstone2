package com.example.capston2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;


    @NotEmpty(message = "Name must not be Empty!...")
    @Column(columnDefinition ="varchar(10) not null")
    private String name;

    @NotEmpty(message = "username must not be empty!...")
    @Size(min = 4, message = "The length must be more than 4 characters")
    @Column(columnDefinition ="varchar(10) not null unique")
    private String username;

    @NotEmpty(message = "Password cannot be empty!")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,20}$",
            message = "Password must be between 8-20 characters, include at least one uppercase letter, one lowercase letter, one number, and one special character.")
    @Column(columnDefinition = "varchar(20) not null")
    private String password;

    @NotEmpty(message = "email cannot be Empty!..")
    @Email(message = "Must be a valid email format")
    @Column(columnDefinition = "varchar(20) not null unique")
    private String email;

    @NotEmpty(message = "Role cannot be Empty!..")
    @Pattern(regexp = "^(Clint|Admin)$",message = "the role must be \"Clint\" or \"Admin\"")
    @Column(columnDefinition = "varchar(10) not null")
    private String role;

    @NotNull(message = " age cannot be null")
    @Column(columnDefinition = "int not null")
    private Integer age;

    @NotEmpty(message = "location must not be Empty!...")
    @Column(columnDefinition ="varchar(200) not null")
    private String location;

    public Customer(Integer customerId, String name, String username, String password, String email, String role, Integer age, String location) {
        this.customerId = customerId;
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.age = age;
        this.location = location;
    }

    public Customer() {
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
