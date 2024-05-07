package com.example.demo.vaccinationCenter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.vaccine.Vaccine;
// import com.example.demo.user.User;

import jakarta.transaction.Transactional;

import java.util.Collections;
// import java.time.LocalDate;
// import java.time.Month;
import java.util.List;
// import java.util.Optional;
import java.util.Optional;


@Service
public class VaccinationCenterService {


    // private final StudentRepository studentRepository;
    
    @Autowired
    VaccinationCenterRepository vaccinationCenterRepository;

    // public StudentService(StudentRepository studentRepository){
    //     this.studentRepository = studentRepository;
    // }
    

    @GetMapping
    public List<VaccinationCenter> getVaccinationCenters() {
        return   vaccinationCenterRepository.findAll();
    }


       public VaccinationCenter getOneVaccinationCenter(Long id) {
        VaccinationCenter centerOptional = vaccinationCenterRepository.findById(id).orElse(null);
        return centerOptional;
    }


    //new servive to get vaccines to each vaccination service by id
    public List<Vaccine> getVaccinesByCenterId(Long centerId) {
        // Find the vaccination center by ID
        Optional<VaccinationCenter> centerOptional = vaccinationCenterRepository.findById(centerId);
        if (centerOptional.isPresent()) {
            // If the center exists, get its associated vaccines
            VaccinationCenter center = centerOptional.get();
            return center.getVaccines();
        } else {
            // If the center does not exist, return an empty list
            return Collections.emptyList();
        }
    }


  
    public VaccinationCenter getVaccinationCenterByOwnerId(Long ownerId) {
        return vaccinationCenterRepository.findByVaccinationCenterOwnerId(ownerId);
    }






    public void addVaccinationcenter(VaccinationCenter vaccinationCenter) {
      
        vaccinationCenterRepository.save(vaccinationCenter);

        System.out.println(vaccinationCenter); 

   }






    @SuppressWarnings("null")
    public void deleteVaccinationCenter(Long vaccinationCenterId) {
        boolean exist = vaccinationCenterRepository.existsById(vaccinationCenterId);
        if (!exist){
            throw new IllegalStateException("vaccinationCenter with id "+ vaccinationCenterId +"not exist");
        }
        vaccinationCenterRepository.deleteById(vaccinationCenterId);
    }








    @SuppressWarnings("null")
    @Transactional
	// public void updataVaccinationCenter(Long vaccinationCenterId, String address, String contact_info , String name) {
    //     @SuppressWarnings("null")

	// 	VaccinationCenter vaccinationCenter = vaccinationCenterRepository.findById(vaccinationCenterId)
    //     .orElseThrow(()-> new IllegalStateException("vaccinationCenter with id "+vaccinationCenterId+" doesnt exist"));

    //        vaccinationCenter.setName(name);
    //        vaccinationCenter.setAddress(address);
    //        vaccinationCenter.setContact_info(contact_info);
       
    // }


    public VaccinationCenter updateVaccinationCenter(Long vaccinationCenterId, VaccinationCenter request) {
        VaccinationCenter vaccinationCenter = vaccinationCenterRepository.findById(vaccinationCenterId)
                .orElseThrow(() -> new IllegalStateException("Vaccination center with id " + vaccinationCenterId + " not found"));

        if (request.getAddress() != null) {
            vaccinationCenter.setAddress(request.getAddress());
        }
        if (request.getContact_info() != null) {
            vaccinationCenter.setContact_info(request.getContact_info());
        }
        if (request.getName() != null) {
            vaccinationCenter.setName(request.getName());
        }

        return vaccinationCenterRepository.save(vaccinationCenter);
    }
}
