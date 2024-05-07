package com.example.demo.vaccine;

// import java.sql.Date;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// import com.example.demo.vaccinationCenter.VaccinationCenter;


@RestController
@RequestMapping("api/Vaccine")

public class VaccineController {
    
    private final VaccineService vaccineService;

    public VaccineController(VaccineService vaccineService) {
        this.vaccineService = vaccineService;
    }

    @GetMapping
    public List<Vaccine> getVaccines(){
        return vaccineService.getVaccines();
    }

    @GetMapping(path = "{vaccineId}")
    public Vaccine getOneVaccine(@PathVariable("vaccineId") Long vaccineId){
       return vaccineService.getOneVaccine(vaccineId);
    }
 
 
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public void addVaccine(@RequestBody Vaccine vaccine){
       vaccineService.addVaccine(vaccine); 
    }
 
 
    
    @DeleteMapping(path = "{vaccineId}")
    @PreAuthorize("hasAuthority('ADMIN')")

    public void deleteVaccine (@PathVariable("vaccineId") Long vaccineId){
        vaccineService.deleteVaccine(vaccineId);
    }
 
 
    // @PutMapping(path = "{vaccineId}")
    // public void updateVaccine(
    //         @PathVariable ("vaccineId") Long vaccineId,
    //         @RequestParam (required = false) String name,
    //         @RequestParam (required = false) String precautions,
    //         // @RequestParam (required = false) VaccinationCenter vaccinationCenter,
    //         @RequestParam (required = false) Date timeGapFirstSecondDose)
    //          {
    //             vaccineService.updataVaccine(vaccineId ,name ,precautions,timeGapFirstSecondDose);
         
    // }

    @PutMapping(path = "{vaccineId}")
    @PreAuthorize("hasAuthority('ADMIN')")

    public void updateVaccine(
                @PathVariable ("vaccineId") Long vaccineId,
                @RequestBody Vaccine vaccine
               )
                 {
                    vaccineService.updataVaccine(vaccineId ,vaccine);
             
        }
    


 
     
 
 

}
