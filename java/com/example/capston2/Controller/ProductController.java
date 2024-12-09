package com.example.capston2.Controller;

import com.example.capston2.ApiResponse.ApiResponse;
import com.example.capston2.Model.Cat;
import com.example.capston2.Model.Product;
import com.example.capston2.Service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/get")
    public ResponseEntity getAllProducts() {
        return ResponseEntity.status(200).body(productService.getAllProduct());
    }

    @PostMapping("/add")
    public ResponseEntity addNewCat(@RequestBody @Valid Product product, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        productService.addNewProduct(product);
        return ResponseEntity.status(200).body(new ApiResponse("Product Added"));
    }



    @PutMapping("/update/{id}")
    public ResponseEntity updateCat(@PathVariable Integer id,@RequestBody @Valid Product product,Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
       productService.updateProduct(product,id);
        return ResponseEntity.status(200).body(new ApiResponse("Cat updated"));
    }


    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
        return ResponseEntity.status(200).body(new ApiResponse("Product deleted successfully"));

    }

/// ///

    @PutMapping("/discount/{id}/{discount}")
    public ResponseEntity addDiscount(@PathVariable Integer id, @PathVariable Double discount) {
        productService.addDiscount(id, discount);
        return ResponseEntity.status(200).body(new ApiResponse("Discount added to product successfully"));

    }

    @GetMapping("/DiscountedProducts/{discount}")
    public ResponseEntity getDiscountProduct(@PathVariable Double discount) {
        List<Product> discountedProducts = productService.getDiscountProduct(discount);
        return ResponseEntity.status(200).body(discountedProducts);
    }


    @GetMapping("/productByCategory/{category}")
    public ResponseEntity  getProductByCategory(@PathVariable  String category) {
        List<Product> productList = productService.getProductByCategory(category);
        return ResponseEntity.status(200).body(productList);
    }

    @GetMapping("/productByCategoryAndAge/category/{category}/min/{min}/max/{max}")
    public ResponseEntity  getProductByPrice(@PathVariable  String category,@PathVariable Integer min , @PathVariable Integer max) {
        List<Product> productList = productService.getProductByCategoryAndAge(category, min, max);
        return ResponseEntity.status(200).body(productList);
    }

    @GetMapping("/productByPrice/min/{min}/max/{max}")
    public ResponseEntity  getProductByPrice(@PathVariable Integer min , @PathVariable Integer max) {
        List<Product> productList = productService.getProductByPrice(min,max);
        return ResponseEntity.status(200).body(productList);
    }

    @GetMapping("/productByName/name/{name}")
    public ResponseEntity  getProductByName( @PathVariable String name) {
        List<Product> productList = productService.getProductByName(name);
        return ResponseEntity.status(200).body(productList);
    }
}

