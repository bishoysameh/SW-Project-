package com.example.demo.certificate;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificateRepository extends JpaRepository<Certificate,Integer> {
    Optional<Certificate> findByName(String fileName);

    Optional<Certificate> findByPatientId(Long patientId);
}