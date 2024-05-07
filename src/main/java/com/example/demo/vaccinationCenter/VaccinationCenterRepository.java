package com.example.demo.vaccinationCenter;

// import java.util.List;

// import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

// import com.example.demo.user.User;


//this interface is responsible for data acess so we used annotation repository 
@Repository
public interface VaccinationCenterRepository extends JpaRepository<VaccinationCenter,Long>{

    VaccinationCenter findByVaccinationCenterOwnerId(Long vaccinationCenterOwner);

   
    // @Query("SELECT s FROM Student s WHERE s.email =?1")
    // Optional<VaccinationCenter> findStudentByEmail(String email);
    // List<VaccinationCenter> findByAdmin(User admin);

}
