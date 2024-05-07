package com.example.demo.vaccinationCenter;


import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.client.HttpClientErrorException.NotFound;

import com.example.demo.vaccine.Vaccine;

import java.util.List;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping(path = "api/VaccinationCenter")

public class VaccinationCenterController {
 private final VaccinationCenterService vaccinationCenterService;

    public VaccinationCenterController(VaccinationCenterService vaccinationCenterService) {
        this.vaccinationCenterService = vaccinationCenterService;
    }

    @GetMapping
    public List<VaccinationCenter> getVaccinationCenters(){
        return vaccinationCenterService.getVaccinationCenters();
    }

    @GetMapping(path = "{vaccinationCenterId}")
    public VaccinationCenter getOneVaccinationCenter(@PathVariable("vaccinationCenterId") Long vaccinationCenterId){
        return vaccinationCenterService.getOneVaccinationCenter(vaccinationCenterId);
    }



    // Endpoint to retrieve vaccines associated with a vaccination center
    @GetMapping("/{centerId}/Vaccines")
    public ResponseEntity<List<Vaccine>> getVaccinesByCenterId(@PathVariable Long centerId) {
        List<Vaccine> vaccines = vaccinationCenterService.getVaccinesByCenterId(centerId);
        return ResponseEntity.ok(vaccines);
    }




@GetMapping(path = "/Owner/{OwnerId}")
 public VaccinationCenter getVaccinationCenterByOwnerId(@PathVariable Long OwnerId ){
    return vaccinationCenterService.getVaccinationCenterByOwnerId(OwnerId);
    
 } 


 


    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")

    public void addVaccinationcenter(@RequestBody VaccinationCenter vaccinationCenter){
        vaccinationCenterService.addVaccinationcenter(vaccinationCenter);
    }


    @DeleteMapping(path = "{vaccinationCenterId}")
    @PreAuthorize("hasAuthority('ADMIN')")

    public void deleteVaccinationCenter (@PathVariable("vaccinationCenterId") Long vaccinationCenterId){
        vaccinationCenterService.deleteVaccinationCenter(vaccinationCenterId);
    }


    // @PutMapping(path = "{vaccinationCenterId}")
    // public void updateVaccinationCenter(
    //         @PathVariable ("vaccinationCenterId") Long vaccinationcenterId,
    //         @RequestParam (required = false) String address,
    //         @RequestParam (required = false) String contact_info,
    //         @RequestParam (required = false) String name)
    //          {
    //             vaccinationCenterService.updataVaccinationCenter(vaccinationcenterId ,address ,contact_info,name);
         
    // }


    @PutMapping("/{vaccinationCenterId}")
    @PreAuthorize("hasAuthority('ADMIN')")

    // public ResponseEntity<?> updateVaccinationCenter(
    public void updateVaccinationCenter(
            @PathVariable("vaccinationCenterId") Long vaccinationCenterId,
            @RequestBody VaccinationCenter request) {
        // VaccinationCenter updatedVaccinationCenter = vaccinationCenterService.updateVaccinationCenter(vaccinationCenterId, request);
            vaccinationCenterService.updateVaccinationCenter(vaccinationCenterId, request);
        // return ResponseEntity.ok(updatedVaccinationCenter);
    }

}
