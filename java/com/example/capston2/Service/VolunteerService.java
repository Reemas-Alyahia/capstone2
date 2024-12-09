package com.example.capston2.Service;

import com.example.capston2.ApiResponse.ApiExption;
import com.example.capston2.Model.Cat;
import com.example.capston2.Model.Customer;
import com.example.capston2.Model.Volunteer;
import com.example.capston2.Repository.CatRepository;
import com.example.capston2.Repository.CustomerRepository;
import com.example.capston2.Repository.VolunteerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VolunteerService {
    private  final VolunteerRepository volunteerRepository;
    private final CustomerRepository customerRepository;
    private final CatRepository catRepository;

    public VolunteerService(VolunteerRepository volunteerRepository, CustomerRepository customerRepository, CatRepository catRepository) {
        this.volunteerRepository = volunteerRepository;
        this.customerRepository = customerRepository;
        this.catRepository = catRepository;
    }

        public List<Volunteer> getAllUser() {
            return volunteerRepository.findAll();
        }


       public void addVolunteer(Volunteer volunteer) {
        if(volunteer.getApermit()==false){
            throw new ApiExption("if you don't have A permit u can't register");
        }
        volunteerRepository.save(volunteer);
   }

    public void updateVolunteer(Volunteer volunteer, Integer id) {
        Volunteer oldVolunteer=volunteerRepository.getReferenceById(id);

        if (oldVolunteer == null) {
            throw new ApiExption("Can't found Volunteer");
        }
        oldVolunteer.setAge(volunteer.getAge());
        oldVolunteer.setName(volunteer.getName());
        oldVolunteer.setApermit(volunteer.getApermit());
        oldVolunteer.setAvailability(volunteer.getAvailability());
        oldVolunteer.setEmail(volunteer.getEmail());
        oldVolunteer.setPassword(volunteer.getPassword());
        volunteerRepository.save(volunteer);
    }

    public void deleteVolunteer(Integer id) {
        Volunteer volunteer=volunteerRepository.findVolunteerByVolunterId(id);
        if (volunteer == null) {
            throw new ApiExption("Can't found Volunteer");
        }
        volunteerRepository.delete(volunteer);
    }
    //approv
    public void approveUser(Integer AdminId, Integer id) {
        Customer admin = customerRepository.findCustomerByCustomerId(AdminId);
        if (admin == null) {
            throw new ApiExption("Can't found the admin Id");
        }
        if (!admin.getRole().equalsIgnoreCase("Admin")) {
            throw new ApiExption("Only Admin can approve users");
        }
     Volunteer volunteer=volunteerRepository.findVolunteerByVolunterId(id);
       if (volunteer == null) {
           throw new ApiExption("Can't found Volunteer");
      }
        volunteer.setActive(true);
       volunteerRepository.save(volunteer);
    }
    /// /Update that volunteer doing
    public String updateCatHealthStatus(Integer volunteerId, Integer catId, String healthStatus) {
        Volunteer volunteer=volunteerRepository.findVolunteerByVolunterId(volunteerId);
        if (volunteer == null ) {
            throw new ApiExption("You are not authorized to update cat status, only volunteers can do this");
        }
        Cat cat = catRepository.findCatByCatId(catId);
        if (cat == null) {
            throw new ApiExption("Cannot find a cat with the provided ID");
        }
        if(cat.getAdoptionStatus().equalsIgnoreCase("Unavailable")){
            cat.setAdoptionStatus("Available");
        }
        cat.setHealthStatus(healthStatus);

        catRepository.save(cat);
        return " رقم القط المنقذ:   " + catId + "تم تحديث الحالة من قبل المتطوع:   " + volunteer.getName();
    }


}
