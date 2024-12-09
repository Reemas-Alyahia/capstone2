package com.example.capston2.Repository;

import com.example.capston2.Model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Integer> {
    Doctor findDoctorByDoctorId(Integer id);


    @Query("select d from Doctor d where d.location=?1 and d.availability=?2 and d.active=?3")
   List <Doctor> getDoctorByLocationAndAvailabilityAndActive(String location, Boolean availability, Boolean active);
}
