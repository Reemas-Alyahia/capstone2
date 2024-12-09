package com.example.capston2.Service;

import com.example.capston2.ApiResponse.ApiExption;
import com.example.capston2.Model.*;
import com.example.capston2.Repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ServiceReguestService {
    private final ServiceReguestRepository serviceReguestRepository;
    private final CatRepository catRepository;
    private final CustomerRepository customerRepository;
    private final DoctorRepository doctorRepository;
    private final VolunteerRepository volunteerRepository;

    public ServiceReguestService(ServiceReguestRepository serviceReguestRepository, CatRepository catRepository, CustomerRepository customerRepository, DoctorRepository doctorRepository, VolunteerRepository volunteerRepository) {
        this.serviceReguestRepository = serviceReguestRepository;
        this.catRepository = catRepository;
        this.customerRepository = customerRepository;
        this.doctorRepository = doctorRepository;
        this.volunteerRepository = volunteerRepository;
    }

    public List<ServiceReguest> getAllService() {
        return serviceReguestRepository.findAll();
    }

//    public String createServiceRequest(ServiceReguest serviceReguest) {
//        Customer customer = customerRepository.findCustomerByCustomerId(serviceReguest.getCustomerId());
//        if (customer == null) {
//            throw new ApiExption("Customer not found with this ID ");
//        }
//        Cat cat = catRepository.findCatByCatId(serviceReguest.getCatId());
//        if (cat == null) {
//            throw new ApiExption("Cat not found with this ID ");
//        }
//        serviceReguestRepository.save(serviceReguest);
//
//        return "Service request created successfully for service type " + serviceReguest.getServiceType();
//    }


    public String createRequest(ServiceReguest serviceReguest, Integer doctorId) {
        Customer customer = customerRepository.findCustomerByCustomerId(serviceReguest.getCustomerId());
        if (customer == null) {
            throw new ApiExption("Customer not found with this ID");
        }

        Cat cat = catRepository.findCatByCatId(serviceReguest.getCatId());
        if (cat == null) {
            throw new ApiExption("Cat not found with this ID");
        }

        Doctor doctor = doctorRepository.findDoctorByDoctorId(doctorId);
        if(doctor==null){
            new ApiExption("Doctor not found with this ID");
        }

        if (doctor.getActive()==false || doctor.getAvailability()==false) {
            throw new ApiExption("Selected doctor is not available or inactive");
        }

        if (!serviceReguest.getServiceType().equals("consultation")&&!serviceReguest.getServiceType().equals("homeVisit")) {
            throw new ApiExption("Service type must be 'consultation' for doctor assignment");
        }
        serviceReguest.setDoctorId(doctorId);
        serviceReguestRepository.save(serviceReguest);

        return "Consultation request created successfully with Dr. " + doctor.getName();
    }

    public void updateServiceDate(ServiceReguest service, Integer requestId) {
        ServiceReguest oldservice = serviceReguestRepository.findServiceReguestByRequestId(requestId);
        if (oldservice == null) {
            throw new ApiExption("Service request not found with ID: " + requestId);
        }

        oldservice.setServiceType(service.getServiceType());
        oldservice.setDetails(service.getDetails());
        oldservice.setCatId(service.getCatId());
        oldservice.setServiceDate(service.getServiceDate());

        serviceReguestRepository.save(oldservice);
    }

    public void deleteService(Integer id) {
        ServiceReguest service = serviceReguestRepository.findServiceReguestByRequestId(id);
        if (service == null) {
            throw new ApiExption("Cannot found this Service");

        }
        serviceReguestRepository.delete(service);
    }

    /// // Services By Type And Date
    public List<ServiceReguest> getServicesByTypeAndDate(Integer doctorId,String serviceType, LocalDate date) {
        Doctor doctor = doctorRepository.findDoctorByDoctorId(doctorId);
        if(doctor==null){
            new ApiExption("Doctor not found with this ID");
        }
        List<ServiceReguest> requests = serviceReguestRepository.getServiceTypeAndServiceDate(serviceType, date);
        if (requests == null) {
            throw new ApiExption("No requests found for service type: " + serviceType + " on date: " + date);
        }
        return requests;
    }

    /// //list for Available Doctor
    public List<Doctor> findAvailableDoctor(Integer customerId) {
        Customer customer = customerRepository.findCustomerByCustomerId(customerId);
        if (customer == null) {
            throw new ApiExption("Customer not found");
        }
        List<Doctor> available = doctorRepository.getDoctorByLocationAndAvailabilityAndActive(customer.getLocation(), true, true);
        if (available == null) {
            throw new ApiExption("No available doctors in your location");
        }
        return available;
    }


    /// //list for Available Volunteer

    public List<Volunteer> findAvailableVolunteer(Integer customerId) {
        Customer customer = customerRepository.findCustomerByCustomerId(customerId);
        if (customer == null) {
            throw new ApiExption("Customer not found");
        }
        List<Volunteer> available = volunteerRepository.geVolunteerByLocationAndAvailabilityAndActive(customer.getLocation(),true,true);
        if (available == null) {
            throw new ApiExption("No available doctors in your location");
        }
        return available;
    }


}