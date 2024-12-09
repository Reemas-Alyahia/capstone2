package com.example.capston2.Repository;

import com.example.capston2.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    Customer findCustomerByCustomerId(Integer customerId);


    @Query("select  u from Customer u where  u.username=?1 and u.password=?2")
    Customer  login(String username, String password);
}



