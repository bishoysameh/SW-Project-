package com.example.demo.reservation;

import java.sql.Date;

import com.example.demo.vaccinationCenter.VaccinationCenter;
import com.example.demo.vaccine.Vaccine;
import com.example.demo.user.User;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservationRequest {
    public static final String User = null;
    private DoseNumber doseNumber;
    private Date reservationDate;
    private ReservationStatus reservationStatus;
    private User patient;
    private VaccinationCenter vaccinationCenter;
    private Vaccine vaccine;

}