package com.example.capston2.Repository;

import com.example.capston2.Model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase,Integer> {

  Purchase findPurchaseByPurchaseId(Integer id);
}
