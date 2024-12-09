package com.example.capston2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Entity
public class Volunteer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer volunterId;

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


    @NotNull(message = " age cannot be null")
    @Min(value = 23,message = "The age must be more than 23")
    @Column(columnDefinition = "int not null default 23")
    private Integer age;

    @NotNull(message = "availability cannot be null!")
    @Column(columnDefinition = "BOOLEAN NOT NULL")
    private Boolean availability;

    @NotNull(message = "Apermit cannot be null!")
    @Column(columnDefinition = "BOOLEAN NOT NULL")
    private Boolean apermit;

    @Column(columnDefinition = "boolean default false")
    private Boolean active=false;

    @NotEmpty(message = "location must not be Empty!...")
    @Column(columnDefinition ="varchar(200) not null")
    private String location;

    public Volunteer(Integer volunterId, String name, String username, String password, String email, Integer age, Boolean availability, Boolean apermit, Boolean active, String location) {
        this.volunterId = volunterId;
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.age = age;
        this.availability = availability;
        this.apermit = apermit;
        this.active = active;
        this.location = location;
    }

    public Volunteer() {

    }

    public Integer getVolunterId() {
        return volunterId;
    }

    public void setVolunterId(Integer volunterId) {
        this.volunterId = volunterId;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }

    public Boolean getApermit() {
        return apermit;
    }

    public void setApermit(Boolean apermit) {
        this.apermit = apermit;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
