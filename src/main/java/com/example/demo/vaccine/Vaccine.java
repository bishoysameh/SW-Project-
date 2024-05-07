package com.example.demo.vaccine;

import java.util.List;

import com.example.demo.certificate.Certificate;
import com.example.demo.reservation.Reservation;

// import java.sql.Date;

import com.example.demo.vaccinationCenter.VaccinationCenter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "vaccines")
public class Vaccine {
    
    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @SequenceGenerator(
            name  = "vaccine_sequence",
            sequenceName = "vaccine sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "vaccine_sequence"
    )
 
    private Long id;
    private String name;
    private String precautions;



// Define the many-to-one relationship with vaccination centers
    @ManyToOne
    @JoinColumn(name = "vaccination_center_id", referencedColumnName = "id")
    private VaccinationCenter vaccinationCenter;



    @OneToMany(mappedBy = "vaccine")
    @JsonIgnore
    private List<Reservation> reservations;



    @OneToMany(mappedBy = "vaccine")
    @JsonIgnore
    private List<Certificate> certificates;


    private int timeGapFirstSecondDose;
    

      public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    // Getters and setters for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    // Getters and setters for precautions
    public String getPrecautions() {
        return precautions;
    }

    public void setPrecautions(String precautions) {
        this.precautions = precautions;
    }
    
    // Getters and setters for vaccinationCenter
    public VaccinationCenter getVaccinationCenter() {
        return vaccinationCenter;
    }

    public void setVaccinationCenter(VaccinationCenter vaccinationCenter) {
        this.vaccinationCenter = vaccinationCenter;
    }
    
    // Getters and setters for timeGapFirstSecondDose
    public int getTimeGapFirstSecondDose() {
        return timeGapFirstSecondDose;
    }

    public void setTimeGapFirstSecondDose(int timeGapFirstSecondDose) {
        this.timeGapFirstSecondDose = timeGapFirstSecondDose;
    }
/********************************* */

public List<Reservation> getReservations() {
    return reservations;
}

public void setReservations(List<Reservation> reservations) {
    this.reservations = reservations;
}
/******************************************** */

public List<Certificate> getCertificates() {
    return certificates;
}

public void setCertificates(List<Certificate> certificates) {
    this.certificates = certificates;
}
/********************************** */

    @Override
    public String toString() {
        return "Vaccine{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", precautions='" + precautions + '\'' +
                 ", vaccinationCenter=" + vaccinationCenter +
                ", timeGapFirstSecondDose=" + timeGapFirstSecondDose +
                '}';
    }
}
