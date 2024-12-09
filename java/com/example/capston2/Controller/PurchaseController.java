package com.example.capston2.Controller;

import com.example.capston2.Model.Purchase;
import com.example.capston2.Service.PurchaseService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/purchase")
public class PurchaseController {
    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }


    @PostMapping("/buy")
    public ResponseEntity purchaseProduct(@RequestBody @Valid Purchase purchase, Errors errors) {
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        String response = purchaseService.purchaseProduct(purchase);
        return ResponseEntity.status(200).body(response);
    }

    @DeleteMapping("return/{purchaseId}")
    public ResponseEntity returnProduct(@PathVariable Integer purchaseId) {
        String response = purchaseService.returnProduct(purchaseId);
        return ResponseEntity.status(200).body(response);
    }
}
