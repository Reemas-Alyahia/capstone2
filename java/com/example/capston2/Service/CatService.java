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
public class CatService {
    private final CatRepository catRepository;
    private final CustomerRepository customerRepository;
    private final VolunteerRepository volunteerRepository;

    public CatService(CatRepository catRepository, CustomerRepository customerRepository, VolunteerRepository volunteerRepository) {
        this.catRepository = catRepository;
        this.customerRepository = customerRepository;
        this.volunteerRepository = volunteerRepository;
    }

    public List<Cat>getAllCats(){
        return catRepository.findAll();
    }
    public void addNewCat(Cat cat){
        Customer customer=customerRepository.findCustomerByCustomerId(cat.getCustomerId());
        if(customer==null){
            throw new ApiExption("Cannot found the id of this customer");
        }
       catRepository.save(cat);    }

    public void updateCat(Cat cat,Integer id){
        Cat oldCat=catRepository.findCatByCatId(id);
        if(oldCat==null){
            throw new ApiExption("Cannot found the id of this Cat");
        }
        oldCat.setAdoptionStatus(cat.getAdoptionStatus());
        oldCat.setName(cat.getName());
        oldCat.setAge(cat.getAge());
        oldCat.setLocation(cat.getLocation());
        oldCat.setHealthStatus(cat.getHealthStatus());

        catRepository.save(oldCat);
    }

    public void deleteCat(Integer id){
        Cat cat=catRepository.findCatByCatId(id);
        if(cat==null){
            throw new ApiExption("Cannot found the cat with this id");

        }
        catRepository.delete(cat);
    }
    public void reportCat(Cat cat) {
        Customer customer=customerRepository.findCustomerByCustomerId(cat.getCustomerId());
        if(customer==null){
            throw new ApiExption("Cannot found the id of this customer");
        }
        cat.setAdoptionStatus("Unavailable");
        cat.setName("Unknown");
        cat.setAge(0);

        catRepository.save(cat);
    }
/// /list for all cat from AdoptionStatus
    public List<Cat>getCatByAdoptionStatus(Integer customerId,String status){
        Customer customer=customerRepository.findCustomerByCustomerId(customerId);
        if(customer==null){
            throw new ApiExption("Cannot found the customer with this id");
        }
        List<Cat>cats=catRepository.getCatByAdoptionStatus(status);
        if(cats.isEmpty()){
            throw new ApiExption("Cannot found the cat with this id");
        }
        return cats;
    }

    public Cat adoptCat(Integer customerId, Integer catId) {
        Customer customer = customerRepository.findCustomerByCustomerId(customerId);
        if (customer == null) {
            throw new ApiExption("Cannot find the customer with this id");
        }
        Cat cat = catRepository.findCatByCatId(catId);
        if(cat==null){
            throw new ApiExption("Cannot found the cat with this id");
        }
        if (!"available".equalsIgnoreCase(cat.getAdoptionStatus())) {
            throw new ApiExption("This cat is not available for adoption");
        }
        cat.setAdoptionStatus("Adopted");
        cat.setCustomerId(customerId);
        return cat;
    }
    /// list that Volunteer can see where the cats that they need to help
    public List<Cat> needsHelp(Integer volID, String location, String healthStatus) {
        Volunteer volunteer=volunteerRepository.findVolunteerByVolunterId(volID);
        if (volunteer == null) {
            throw new ApiExption("Can't found Volunteer");
        }
        List<Cat> cats = catRepository.getCatByLocationAndHealthStatus(location, healthStatus);
        if (cats.isEmpty()) {
            throw new ApiExption("There no cats in this location or they dont needs help now");
        }
        return cats;
    }


}
