package com.example.capston2.Repository;

import com.example.capston2.Model.ServiceReguest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ServiceReguestRepository extends JpaRepository<ServiceReguest,Integer> {
    ServiceReguest findServiceReguestByRequestId(Integer requestId);


  /// //JPQL////
    @Query("select s from ServiceReguest s where s.serviceType=?1 and s.serviceDate=?2")
    List<ServiceReguest> getServiceTypeAndServiceDate(String serviceType, LocalDate serviceDate);


}

