package com.example.capston2.Service;

import com.example.capston2.ApiResponse.ApiExption;
import com.example.capston2.Model.Customer;
import com.example.capston2.Model.Doctor;
import com.example.capston2.Model.Volunteer;
import com.example.capston2.Repository.CustomerRepository;
import com.example.capston2.Repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;
    private final CustomerRepository customerRepository;
    public DoctorService(DoctorRepository doctorRepository, CustomerRepository customerRepository) {
        this.doctorRepository = doctorRepository;
        this.customerRepository = customerRepository;
    }

    public List<Doctor> getAllUser() {
         return doctorRepository.findAll();
     }


    public void addDoctor(Doctor doctor) {
        if(doctor.getApermit()==false){
            throw new ApiExption("if you don't have A permit u can't register");
        }
        doctorRepository.save(doctor);
    }

    public void updateDoctor(Doctor doctor, Integer id) {
        Doctor oldDoctor=doctorRepository.findDoctorByDoctorId(id);

        if (oldDoctor == null) {
            throw new ApiExption("Can't found Doctor");
        }
        oldDoctor.setAge(doctor.getAge());
        oldDoctor.setName(doctor.getName());
        oldDoctor.setApermit(doctor.getApermit());
        oldDoctor.setAvailability(doctor.getAvailability());
        oldDoctor.setEmail(doctor.getEmail());
        oldDoctor.setPassword(doctor.getPassword());

        doctorRepository.save(doctor);
    }

    public void deleteDoctor(Integer id) {
        Doctor doctor=doctorRepository.findDoctorByDoctorId(id);
        if (doctor == null) {
            throw new ApiExption("Can't found Doctor");
        }
        doctorRepository.delete(doctor);
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
        Doctor doctor=doctorRepository.findDoctorByDoctorId(id);
        if (doctor == null) {
            throw new ApiExption("Can't found Doctor");
        }
        doctor.setActive(true);
        doctorRepository.save(doctor);
    }


}
