package com.example.demo.user;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.certificate.Certificate;
import com.example.demo.reservation.Reservation;
import com.example.demo.vaccinationCenter.VaccinationCenter;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@Entity

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class User implements UserDetails {
@Id
@GeneratedValue
    private Long id;

    // private String firstName;

    // private String lastName;
   private String username;

    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    
    
     private Role role ;
    


    //CAN CONVERT IT INTO ENUM 
    private String status;



@OneToOne(mappedBy = "vaccinationCenterOwner")
@JsonIgnore
private VaccinationCenter vaccinationCenterOwner;


/*********************************** */
// @OneToMany(mappedBy = "patient" , cascade = CascadeType.ALL)
@OneToMany(mappedBy = "patient")
@JsonIgnore
private List<Reservation> reservations;



/************************************** */
@OneToMany(mappedBy = "patient")
@JsonIgnore
private List<Certificate> certificates;




 
//***************** */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }
 


    // public User () {

    // }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }



    public String getUserName() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }


    public VaccinationCenter getVaccinationCenter(){
        return vaccinationCenterOwner;
    }


    public void setVaccinationCenter(VaccinationCenter vaccinationCenterOwner){
        this.vaccinationCenterOwner = vaccinationCenterOwner;
    }

/**************************** */
    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }


/************************************************************* */
    public List<Certificate> getCertificates() {
        return certificates;
    }

    public void setCertificates(List<Certificate> certificates) {
        this.certificates = certificates;
    }
}
