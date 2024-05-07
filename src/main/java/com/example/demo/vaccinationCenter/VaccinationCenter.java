package com.example.demo.vaccinationCenter;

import java.util.List;

import com.example.demo.certificate.Certificate;
import com.example.demo.reservation.Reservation;
import com.example.demo.user.User;
import com.example.demo.vaccine.Vaccine;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;

// import java.time.LocalDate;

// import com.example.demo.user.User;


@Entity
@Table(name = "vaccination_centers")

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

public class VaccinationCenter {


    @Id
    @SequenceGenerator(
            name  = "vaccination_center_sequence",
            sequenceName = "vaccination center sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "vaccination_center_sequence"
    )

    private long id;
    private String name;
    // private String email;
    // private LocalDate dob;
    private String address;

    @OneToOne
    @JoinColumn(name = "vaccinationCenterOwner_id", referencedColumnName = "id")
    private User vaccinationCenterOwner;
    
    private String contact_info;


    // Define the one-to-many relationship with vaccines
    @JsonIgnore
    @OneToMany(mappedBy = "vaccinationCenter", cascade = CascadeType.ALL)
    private List<Vaccine> vaccines;



    @JsonIgnore
    @OneToMany(mappedBy = "vaccinationCenter")
    private List<Reservation> reservations;

/*************************************** */
    @JsonIgnore
    @OneToMany(mappedBy = "vaccinationCenter")
    private List<Certificate> certificates;




    public VaccinationCenter() {
    }

    public VaccinationCenter(long id, String name, String address, String contact_info) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contact_info = contact_info;
    }

    public VaccinationCenter( String name, String address, String contact_info) {
      
        this.name = name;
        this.address = address;
        this.contact_info = contact_info;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact_info() {
        return contact_info;
    }

    public void setContact_info(String contact_info) {
        this.contact_info = contact_info;
    }

    public List<Vaccine> getVaccines() {
        return vaccines;
    }






    public User getUsers(){
        return vaccinationCenterOwner;
     }
    
     public void setUsers(User vaccinationCenterOwner){
        this.vaccinationCenterOwner = vaccinationCenterOwner;
     }
    
    //**************************

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }



    /***************************************** */
    public List<Certificate> getCertificates() {
        return certificates;
    }

    public void setCertificates(List<Certificate> certificates) {
        this.certificates = certificates;
    }




    @Override
    public String toString() {
        return "Vaccination Center{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + address + '\'' +
                ", dob=" + contact_info +
                '}';
    }

  
}
