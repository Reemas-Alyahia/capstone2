package com.example.capston2.Controller;

import com.example.capston2.ApiResponse.ApiResponse;
import com.example.capston2.Model.Volunteer;
import com.example.capston2.Service.VolunteerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/volunteer")
public class VolunteerController {
    private final VolunteerService volunteerService;

    public VolunteerController(VolunteerService volunteerService) {
        this.volunteerService = volunteerService;
    }

    @GetMapping("/get")
   public ResponseEntity getAllVolunteer(){
       return ResponseEntity.status(200).body(volunteerService.getAllUser());
   }

   @PostMapping("/add")
    public ResponseEntity addnewVolunteer(@RequestBody @Valid Volunteer volunteer, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
    volunteerService.addVolunteer(volunteer);
        return ResponseEntity.status(200).body(new ApiResponse("Volunteer Added"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateVolunteer(@PathVariable Integer id,@RequestBody @Valid Volunteer volunteer,Errors errors){
       if(errors.hasErrors()){
           return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
   volunteerService.updateVolunteer(volunteer,id);
       return ResponseEntity.status(200).body(new ApiResponse("Volunteer updated"));
    }


    @DeleteMapping("/delete/{id}")
   public ResponseEntity deleteVolunteer(@PathVariable Integer id){
       volunteerService.deleteVolunteer(id);
               return ResponseEntity.status(200).body(new ApiResponse("Volunteer Deleted"));
    }


        @PutMapping("/approve/adminId/{adminId}/volunteerId/{userId}")
    public ResponseEntity approve(@PathVariable Integer adminId,@PathVariable Integer userId){
       volunteerService.approveUser(adminId,userId);
       return ResponseEntity.status(200).body(new ApiResponse("VolunteerApproved"));
    }

    @PutMapping("/updateHealth/volunteerId/{volunteerId}/cats/{catId}/HealthStatus/{healthStatus}")
    public ResponseEntity updateCatHealthStatus(@PathVariable Integer volunteerId,@PathVariable String healthStatus ,@PathVariable Integer catId) {
        String result = volunteerService.updateCatHealthStatus(volunteerId,catId,healthStatus);
        return ResponseEntity.status(200).body(result);
    }


}
