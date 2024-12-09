package com.example.capston2.Repository;

import com.example.capston2.Model.Doctor;
import com.example.capston2.Model.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VolunteerRepository extends JpaRepository<Volunteer,Integer> {
    Volunteer findVolunteerByVolunterId(Integer id);



    @Query("select v from Volunteer v where v.location=?1 and v.availability=?2 and v.active=?3")
    List<Volunteer> geVolunteerByLocationAndAvailabilityAndActive(String location, Boolean availability, Boolean active);
}
