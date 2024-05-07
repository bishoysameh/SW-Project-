package com.example.demo.reservation;

import java.util.Calendar;
import java.util.Date;
// import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class ReservationService {
    
    @Autowired
    private ReservationRepository reservationRepository;

    public void reserveVaccine(ReservationRequest request) throws ReservationException {
        // Check if the request has a valid dose number
        if (request.getDoseNumber() == null || ((!request.getDoseNumber().equals(DoseNumber.FIRST_DOSE)
                && !request.getDoseNumber().equals(DoseNumber.SECOND_DOSE)))  ) {
            throw new ReservationException("Invalid dose number");
        }



        // Check if the user already has a reservation for the same vaccine in the same vaccination center
        List<Reservation> existingReservations = reservationRepository.findByPatientIdAndVaccineIdAndVaccinationCenterId(
                request.getPatient().getId(), request.getVaccine().getId(), request.getVaccinationCenter().getId());

         if (existingReservations.isEmpty())
         {
            if (request.getDoseNumber().equals(DoseNumber.SECOND_DOSE))
            {
                throw new ReservationException("you must reserve first dose first");
            }
         }

        if (!existingReservations.isEmpty())
         {
            if (request.getDoseNumber().equals(DoseNumber.FIRST_DOSE)) 
            {
                throw new ReservationException("You are already reserved the first dose of this vaccine in this center");
            } 
            if (request.getDoseNumber().equals(DoseNumber.SECOND_DOSE) && existingReservations.get(0).getStatus().equals(ReservationStatus.PENDING)) 
            {
                throw new ReservationException("you cant reserve second dose until first dose approved");
            }

            if (request.getDoseNumber().equals(DoseNumber.SECOND_DOSE) && existingReservations.get(0).getStatus().equals(ReservationStatus.APPROVED) && existingReservations.size() == 1) 
                {
                    Reservation firstDoseReservation = existingReservations.get(0);

                    int GapTimeForVaccine = firstDoseReservation.getVaccine().getTimeGapFirstSecondDose();
                    Date firstDoseDate = firstDoseReservation.getReservationDate(); 
                    //date which the user request to reserve second dose
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(firstDoseDate);
                    calendar.add(Calendar.DAY_OF_MONTH, GapTimeForVaccine);

                    //date after add days between 2 doses
                    Date updatedDate = calendar.getTime();


                    Date requsestSecondDoseDate = request.getReservationDate();
                    if (updatedDate.compareTo(requsestSecondDoseDate) > 0) 
                    {
                        throw new ReservationException("you cant reserve second dose before gap time between 2 doses");
                    }


                }   


            if(existingReservations.size() == 2 )
            {
                throw new ReservationException("You are already reserved both doses of this vaccine in this center");
            }
        }
        



   
        var reservation = com.example.demo.reservation.Reservation.builder()
        .reservationDate(request.getReservationDate())
        .doseNumber(request.getDoseNumber())
        .patient(request.getPatient())
        .vaccinationCenter(request.getVaccinationCenter())
        .vaccine(request.getVaccine())
        .status(ReservationStatus.PENDING)
        .build();



        reservationRepository.save(reservation);
    }





    public void approveDose(Long userId) {
        Optional<Reservation> userOptional = reservationRepository.findById(userId);
        if (userOptional.isPresent()) {
            Reservation reservation = userOptional.get();
            reservation.setStatus(ReservationStatus.APPROVED);
            reservationRepository.save(reservation);
        } else {
            throw new EntityNotFoundException("User not found with ID: " + userId);
        }
    }


    public List<Reservation> getAllPendingFirstDoseByVaccinationCenter(Long vaccinationCenterId) {
        return reservationRepository.findByVaccinationCenterIdAndDoseNumberAndStatus(vaccinationCenterId, DoseNumber.FIRST_DOSE, ReservationStatus.PENDING);
    }

    

    public List<Reservation> getAllPendingSecondDoseByVaccinationCenter(Long vaccinationCenterId) {
        return reservationRepository.findByVaccinationCenterIdAndDoseNumberAndStatus(vaccinationCenterId, DoseNumber.SECOND_DOSE, ReservationStatus.PENDING);
    }



    public List<Reservation> getAllApprovedSecondDoseByVaccinationCenter(Long vaccinationCenterId) {
        return reservationRepository.findByVaccinationCenterIdAndDoseNumberAndStatus(vaccinationCenterId, DoseNumber.SECOND_DOSE, ReservationStatus.APPROVED);
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }


    public List<Reservation> getPatientsReservedAtVaccinationCenter(Long vaccinationCenterId) {
        return reservationRepository.findByVaccinationCenterId(vaccinationCenterId);
    }
}


