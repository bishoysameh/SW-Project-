package com.example.demo.reservation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByPatientIdAndVaccineIdAndVaccinationCenterId(Long patientId, Long vaccineId, Long vaccinationCenterId);

    List<Reservation> findByVaccinationCenterIdAndDoseNumberAndStatus(Long vaccinationCenterId, DoseNumber doseNumber, ReservationStatus status);

    Reservation findByPatientIdAndVaccineIdAndDoseNumberAndVaccinationCenterId(Long patientId, Long vaccineId, DoseNumber doseNumber, Long vaccinationCenterId);

    List<Reservation> findByVaccinationCenterId(Long vaccinationCenterId);

}
