package com.example.demo.vaccine;

// import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// import com.example.demo.vaccinationCenter.VaccinationCenter;

import jakarta.transaction.Transactional;

@Service
public class VaccineService {

    @Autowired
    VaccineRepository vaccineRepository;

    public List<Vaccine> getVaccines(){
        return vaccineRepository.findAll();
    }




    public Vaccine getOneVaccine(Long id) {
        Vaccine vaccineOptional = vaccineRepository.findById(id).orElse(null);
        return vaccineOptional;
    }












    public void addVaccine(Vaccine vaccine) { 
        vaccineRepository.save(vaccine);
        // System.out.println(vaccinationCenter); 
   }






    @SuppressWarnings("null")
    public void deleteVaccine(Long vaccineId) {
        boolean exist = vaccineRepository.existsById(vaccineId);
        if (!exist){
            throw new IllegalStateException("vaccine with id "+ vaccineId +"not exist");
        }
        vaccineRepository.deleteById(vaccineId);
    }








    // @SuppressWarnings("null")
    // @Transactional
	// public void updataVaccine(Long vaccineId, String name, String precautions , Date timeGapFirstSecondDose) {
    //     @SuppressWarnings("null")

	// 	Vaccine vaccine = vaccineRepository.findById(vaccineId)
    //     .orElseThrow(()-> new IllegalStateException("vaccine with id "+vaccineId+" doesnt exist"));

    //        vaccine.setName(name);
    //        vaccine.setPrecautions(precautions);
    //     //    vaccine.setVaccinationCenter(vaccinationCenter);
    //        vaccine.setTimeGapFirstSecondDose(timeGapFirstSecondDose);

    
    // }

      @SuppressWarnings("null")
      @Transactional
    public Vaccine updataVaccine(Long vaccineId, Vaccine request) {
        @SuppressWarnings("null")

		Vaccine vaccine = vaccineRepository.findById(vaccineId)
        .orElseThrow(()-> new IllegalStateException("vaccine with id "+vaccineId+" doesnt exist"));

        if (request.getName() != null) {
            vaccine.setName(request.getName());
        }
        if (request.getPrecautions() != null) {
            vaccine.setPrecautions(request.getPrecautions());
        }
        if (request.getTimeGapFirstSecondDose() >= 0 ) {
            vaccine.setTimeGapFirstSecondDose(request.getTimeGapFirstSecondDose());
        }

        return vaccineRepository.save(vaccine);

    
    }

}
