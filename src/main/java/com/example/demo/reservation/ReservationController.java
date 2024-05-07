package com.example.demo.reservation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/Reservation")
public class ReservationController {
    
    @Autowired
    private ReservationService reservationService;

    @PostMapping
    public ResponseEntity<String> reserveVaccine(@RequestBody ReservationRequest request) {
        try {
            reservationService.reserveVaccine(request);
            return ResponseEntity.ok("Reservation successful");
        } catch (ReservationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @PutMapping("/{reservationId}/approveDose")
    @PreAuthorize("hasAuthority('VACCINATION_CENTER')")

    public ResponseEntity<Void> approveDose(@PathVariable Long reservationId) {
        reservationService.approveDose(reservationId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/pendingFirstDose/{vaccinationCenterId}")
    @PreAuthorize("hasAuthority('VACCINATION_CENTER')")

    public ResponseEntity<List<Reservation>> getAllPendingFirstDoseReservationsByVaccinationCenter(@PathVariable Long vaccinationCenterId) {
        List<Reservation> reservations = reservationService.getAllPendingFirstDoseByVaccinationCenter(vaccinationCenterId);
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/pendingSecondDose/{vaccinationCenterId}")
    @PreAuthorize("hasAuthority('VACCINATION_CENTER')")

    public ResponseEntity<List<Reservation>> getAllPendingSecondDoseByVaccinationCenter(@PathVariable Long vaccinationCenterId) {
        List<Reservation> reservations = reservationService.getAllPendingSecondDoseByVaccinationCenter(vaccinationCenterId);
        return ResponseEntity.ok(reservations);
    }


    @GetMapping("/approvedSecondDose/{vaccinationCenterId}")
    @PreAuthorize("hasAuthority('VACCINATION_CENTER')")

    public ResponseEntity<List<Reservation>> getAllApprovedSecondDoseByVaccinationCenter(@PathVariable Long vaccinationCenterId) {
        List<Reservation> reservations = reservationService.getAllApprovedSecondDoseByVaccinationCenter(vaccinationCenterId);
        return ResponseEntity.ok(reservations);
    }

    

    @GetMapping
    @PreAuthorize("hasAuthority('VACCINATION_CENTER')")

    public ResponseEntity<List<Reservation>> getAllReservations() {
        List<Reservation> reservations = reservationService.getAllReservations();
        return ResponseEntity.ok(reservations);
    }


    @GetMapping("/{vaccinationCenterId}/patientsReserved")
    @PreAuthorize("hasAuthority('VACCINATION_CENTER')")

    public ResponseEntity<List<Reservation>> getPatientsReservedAtVaccinationCenter(@PathVariable Long vaccinationCenterId) {
        List<Reservation> patientReservations = reservationService.getPatientsReservedAtVaccinationCenter(vaccinationCenterId);
        return ResponseEntity.ok(patientReservations);
    }
}
