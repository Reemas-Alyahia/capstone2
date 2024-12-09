package com.example.capston2.Repository;

import com.example.capston2.Model.Cat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatRepository extends JpaRepository<Cat,Integer> {
    Cat findCatByCatId(Integer catId);

    /// JPQL
    @Query("select c from Cat c where c.location=?1 and c.healthStatus=?2")
   List <Cat> getCatByLocationAndHealthStatus(String location, String healthStatus);

    @Query("select c from Cat c where c.age>=?1 and c.age<=?2")
    List<Cat> findByAgePleas(Integer min,Integer max);

    @Query("select c from Cat c where  c.adoptionStatus=?1")
  List<Cat>getCatByAdoptionStatus(String status);


}
