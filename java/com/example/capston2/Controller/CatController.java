package com.example.capston2.Controller;

import com.example.capston2.ApiResponse.ApiResponse;
import com.example.capston2.Model.Cat;
import com.example.capston2.Service.CatService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cat")
public class CatController {
private final CatService catService;

    public CatController(CatService catService) {
        this.catService = catService;
    }
    @GetMapping("/get")
    public ResponseEntity getAllCats() {
        return  ResponseEntity.status(200).body(catService.getAllCats());
    }

    @PostMapping("/add")
    public ResponseEntity addNewCat(@RequestBody @Valid Cat cat, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
       catService.addNewCat(cat);
        return ResponseEntity.status(200).body(new ApiResponse("Cat Added"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateCat(@PathVariable Integer id,@RequestBody @Valid Cat cat,Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
       catService.updateCat(cat,id);
        return ResponseEntity.status(200).body(new ApiResponse("Cat updated"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCat(@PathVariable Integer id){
        catService.deleteCat(id);
        return ResponseEntity.status(200).body(new ApiResponse("Cat Deleted"));
    }

    @PostMapping("/report")
    public ResponseEntity reportForCat(@RequestBody @Valid Cat cat, Errors errors) {
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        catService.reportCat(cat);
        return ResponseEntity.status(200).body(new ApiResponse("تم رفع البلاغ ،شكرا لك"));
    }

    @GetMapping("/adopted/{customerId}/{status}")
    public ResponseEntity getCatByAdoptionStatus(@PathVariable Integer customerId, @PathVariable String status) {
        List<Cat>cats= catService.getCatByAdoptionStatus(customerId, status);
        return ResponseEntity.status(200).body(cats);
    }

    @PostMapping("/catAdopted/{customerId}/{catId}")
    public ResponseEntity catAdoption(@PathVariable Integer customerId, @PathVariable Integer catId) {
        Cat cats= catService.adoptCat(customerId,catId);
        return ResponseEntity.status(200).body(cats);
    }

  @GetMapping("/help/Volunteer/{userID}/location/{location}/HealthStatus/{healthStatus}")
  public ResponseEntity catsNeedHelp(@PathVariable Integer userID, @PathVariable String location,@PathVariable String healthStatus){
          List<Cat>cats=catService.needsHelp(userID, location, healthStatus);
     return ResponseEntity.status(200).body(cats);
 }


}
