package com.example.capston2.Controller;

import com.example.capston2.ApiResponse.ApiResponse;
import com.example.capston2.Model.Cat;
import com.example.capston2.Model.Customer;
import com.example.capston2.Service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/get")
    public ResponseEntity getAllCustomer() {
        return ResponseEntity.status(200).body(customerService.getAllCustomer());
    }

    @PostMapping("/add")
    public ResponseEntity addNewCustomer(@RequestBody @Valid Customer customer, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
      customerService.addNewCustomer(customer);
        return ResponseEntity.status(200).body(new ApiResponse("Customer Added"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateCustomer(@PathVariable Integer id, @RequestBody @Valid Customer customer, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
       customerService.updateCustomer(customer,id);
        return ResponseEntity.status(200).body(new ApiResponse("Customer updated"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCustomer(@PathVariable Integer id){
        customerService.deleteCustomer(id);
        return ResponseEntity.status(200).body(new ApiResponse("Customer Deleted"));
    }
///
    @GetMapping("/{customerId}/report/{catId}")
    public Cat checkReportedCat(@PathVariable Integer customerId, @PathVariable Integer catId) {
        return customerService.checkReportedCat(customerId, catId);}

    @GetMapping("/logIn/username/{username}/passwords/{pass}")
    public ResponseEntity logIn(@PathVariable String username,@PathVariable String pass){
       Customer customer=customerService.logIn(username, pass);
        return ResponseEntity.status(200).body(customer);
    }


}
