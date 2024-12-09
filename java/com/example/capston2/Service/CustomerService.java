package com.example.capston2.Service;

import com.example.capston2.ApiResponse.ApiExption;
import com.example.capston2.Model.Cat;
import com.example.capston2.Model.Customer;
import com.example.capston2.Repository.CatRepository;
import com.example.capston2.Repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CatRepository catRepository;
    public CustomerService(CustomerRepository customerRepository, CatRepository catRepository) {
        this.customerRepository = customerRepository;
        this.catRepository = catRepository;
    }

    public List<Customer> getAllCustomer(){
        return customerRepository.findAll();
    }
    public void addNewCustomer(Customer customer){
        customerRepository.save(customer);   }

    public void updateCustomer(Customer customer,Integer id){
        Customer oldCustomer=customerRepository.findCustomerByCustomerId(id);
        if(oldCustomer==null){
            throw new ApiExption("Cannot found the id of this customer");
        }
       oldCustomer.setName(customer.getName());
        oldCustomer.setEmail(customer.getEmail());
        oldCustomer.setPassword(customer.getPassword());
        oldCustomer.setAge(customer.getAge());
        oldCustomer.setUsername(customer.getUsername());
    }

    public void deleteCustomer(Integer id){
        Customer customer=customerRepository.findCustomerByCustomerId(id);
        if(customer==null){
            throw new ApiExption("Cannot found the id of this Customer");
        }
        customerRepository.delete(customer);
    }

    /// //check for report
    public Cat checkReportedCat(Integer customerId, Integer catId) {
        Customer customer = customerRepository.findCustomerByCustomerId(customerId);
        if (customer == null) {
            throw new ApiExption("Cannot find a customer with the provided ID");}

        Cat cat = catRepository.findCatByCatId(catId);
        if (cat == null) {
            throw new ApiExption("Cannot find a cat with the provided ID");}

        if (!cat.getCustomerId().equals(customerId)) {
            throw new ApiExption("You are not authorized to view this cat's status"); }
        return cat;
    }


    public Customer logIn(String username,String passwords){
        Customer customer=customerRepository.login(username, passwords);
        if(customer==null){
            throw new ApiExption("Cannot found clint like this");
        }
        return customer;
    }

}
