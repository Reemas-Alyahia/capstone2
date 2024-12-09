package com.example.capston2.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class ServiceReguest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer requestId;

    @NotNull(message = " Cat id cannot be null")
    @Column(columnDefinition = "int not null")
    private Integer catId;

    @NotEmpty(message = "details must not be Empty!...")
    @Column(columnDefinition ="varchar(200) not null")
    private String details;

    @NotNull(message = "Customer id cannot be null")
    @Column(columnDefinition = "int not null")
    private Integer customerId;

    @Column(columnDefinition = "int" )
    private Integer doctorId;
    @NotEmpty(message = "Role cannot be Empty!..")
    @Pattern(regexp = "^(consultation|homeVisit|adoption)$",
            message = "the service Type must be \"consultation\" or \"homeVisit\" or \"adoption\" ")
    @Column(columnDefinition = "varchar(30) not null")
    private String serviceType;

    @NotNull(message = "Service Date cannot be Empty!..")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(columnDefinition = "DATE", nullable = false)
    private LocalDate serviceDate;

    public ServiceReguest(Integer requestId, Integer catId, String details, Integer customerId, Integer doctorId, String serviceType, LocalDate serviceDate) {
        this.requestId = requestId;
        this.catId = catId;
        this.details = details;
        this.customerId = customerId;
        this.doctorId = doctorId;
        this.serviceType = serviceType;
        this.serviceDate = serviceDate;
    }

    public ServiceReguest() {
    }

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public LocalDate getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(LocalDate serviceDate) {
        this.serviceDate = serviceDate;
    }
}
