package com.example.capston2.Service;

import com.example.capston2.ApiResponse.ApiExption;
import com.example.capston2.Model.Customer;
import com.example.capston2.Model.Product;
import com.example.capston2.Model.Purchase;
import com.example.capston2.Repository.CustomerRepository;
import com.example.capston2.Repository.ProdectRepository;
import com.example.capston2.Repository.PurchaseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;
    private final ProdectRepository prodectRepository;
    private final CustomerRepository customerRepository;

    public PurchaseService(PurchaseRepository purchaseRepository, ProdectRepository prodectRepository, CustomerRepository customerRepository) {
        this.purchaseRepository = purchaseRepository;
        this.prodectRepository = prodectRepository;
        this.customerRepository = customerRepository;
    }

    public String purchaseProduct(Purchase purchase) {
        Customer customer = customerRepository.findCustomerByCustomerId(purchase.getCustomerId());

        Product product = prodectRepository.findProductByProductIdAndStock(purchase.getProductId(),purchase.getQuantity());
               if(product==null){
                   throw new ApiExption("Product not found or insufficient stock");
               }
        product.setStock(product.getStock() - purchase.getQuantity());
        prodectRepository.save(product);
        purchase.setPurchaseDate(LocalDate.now());
        purchaseRepository.save(purchase);

        return "Purchase successful! Product: " + product.getName() + ", Quantity: " + purchase.getQuantity();
    }


    public String returnProduct(Integer purchaseId){
        Purchase purchase=purchaseRepository.findPurchaseByPurchaseId(purchaseId);
        if(purchase==null){
            throw new ApiExption("purchase not found");
        }
        Product product=prodectRepository.findProductByProductId(purchase.getProductId());
        if(product==null){
            throw new ApiExption("product not found");
        }
        product.setStock(product.getStock()+purchase.getQuantity());
        prodectRepository.save(product);


        purchaseRepository.delete(purchase);

        return "Purchase successful! Product: " + product.getName() + " Quantity: " + purchase.getQuantity();
    }

}
