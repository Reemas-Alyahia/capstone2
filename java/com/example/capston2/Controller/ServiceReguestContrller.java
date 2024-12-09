package com.example.capston2.Controller;

import com.example.capston2.ApiResponse.ApiResponse;
import com.example.capston2.Model.Doctor;
import com.example.capston2.Model.ServiceReguest;
import com.example.capston2.Model.Volunteer;
import com.example.capston2.Service.ServiceReguestService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/service")
public class ServiceReguestContrller {
    private final ServiceReguestService serviceReguestService;

    public ServiceReguestContrller(ServiceReguestService serviceReguestService) {
        this.serviceReguestService = serviceReguestService;
    }

    @GetMapping("/get")
    public ResponseEntity getAlluser(ServiceReguest serviceReguest) {
        return ResponseEntity.status(200).body(serviceReguestService.getAllService());
    }
@PostMapping("/add/doctor/{doctorId}")
public ResponseEntity createServiceRequest(@PathVariable Integer doctorId,@RequestBody @Valid ServiceReguest serviceReguest,Errors errors){
    if(errors.hasErrors()){
        return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
    }
serviceReguestService.createRequest(serviceReguest,doctorId);
    return ResponseEntity.status(200).body(new ApiResponse("Service request added successfully!"));

}

    @PutMapping("/update/{requestId}")
    public ResponseEntity updateServiceRequest( @PathVariable Integer requestId, @RequestBody @Valid ServiceReguest service,Errors errors) {
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        serviceReguestService.updateServiceDate(service, requestId);
        return ResponseEntity.status(200).body(new ApiResponse("Service request updated successfully!"));
     }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteServiceRequest(@PathVariable Integer id) {
        serviceReguestService.deleteService(id);
        return ResponseEntity.status(200).body(new ApiResponse("Service request deleted successfully!"));

    }


    @GetMapping("/filter/{doctorId}/{serviceType}/{date}")
   public ResponseEntity getServicesByTypeAndDate(@PathVariable Integer doctorId,@PathVariable String serviceType, @PathVariable LocalDate date) {
        List<ServiceReguest> requests = serviceReguestService.getServicesByTypeAndDate(doctorId,serviceType, date);
       return ResponseEntity.status(200).body(requests);
   }

    @GetMapping("/availableDoctor-helper/{customerId}")
    public ResponseEntity findAvailableDoctorOrVolunteer(@PathVariable Integer customerId) {
        List<Doctor> availableUser = serviceReguestService.findAvailableDoctor(customerId);
       return ResponseEntity.status(200).body(availableUser);
    }


    @GetMapping("/availableVolunteer-helper/{customerId}")
    public ResponseEntity findAvailableVolunteer(@PathVariable Integer customerId) {
        List<Volunteer> availableUser = serviceReguestService.findAvailableVolunteer(customerId);
        return ResponseEntity.status(200).body(availableUser);
    }


}