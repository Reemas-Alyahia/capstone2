package com.example.capston2.Controller;

import com.example.capston2.ApiResponse.ApiResponse;
import com.example.capston2.Model.Doctor;
import com.example.capston2.Model.Volunteer;
import com.example.capston2.Service.DoctorService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/doctor")
public class DoctorController {
    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/get")
    public ResponseEntity getAllDoctor(){
        return ResponseEntity.status(200).body(doctorService.getAllUser());
    }

    @PostMapping("/add")
    public ResponseEntity addNewDoctor(@RequestBody @Valid Doctor doctor, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        doctorService.addDoctor(doctor);
        return ResponseEntity.status(200).body(new ApiResponse("Doctor Added"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateDoctor(@PathVariable Integer id,@RequestBody @Valid Doctor doctor,Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        doctorService.updateDoctor(doctor,id);
        return ResponseEntity.status(200).body(new ApiResponse("Doctor updated"));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteDoctor(@PathVariable Integer id){
        doctorService.deleteDoctor(id);
        return ResponseEntity.status(200).body(new ApiResponse("Doctor Deleted"));
    }


    @PutMapping("/approve/adminId/{adminId}/doctorId/{userId}")
    public ResponseEntity approve(@PathVariable Integer adminId,@PathVariable Integer userId){
        doctorService.approveUser(adminId,userId);
        return ResponseEntity.status(200).body(new ApiResponse("Doctor Approved"));
    }

}
