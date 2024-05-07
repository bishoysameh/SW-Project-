package com.example.demo.certificate;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificataRepository extends JpaRepository<Certificate,Long>{


        Optional<Certificate> findByName(String fileName);
        Optional<Certificate> findByPatientIdAndVaccineIdAndVaccinationCenterId(Long patientId, Long vaccineId, Long vaccinationCenterId);

    }

