package com.example.demo.certificate;

import java.nio.file.Files;
// import java.nio.file.Paths;
// import java.nio.file.StandardCopyOption;

// import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.user.User;
import com.example.demo.vaccinationCenter.VaccinationCenter;
import com.example.demo.vaccine.Vaccine;

// import io.jsonwebtoken.io.IOException;
// import io.micrometer.common.util.StringUtils;
// import jakarta.persistence.criteria.Path;



// import com.javatechie.entity.FileData;
// import com.javatechie.entity.ImageData;
// import com.javatechie.respository.FileDataRepository;
// import com.javatechie.respository.StorageRepository;
//  import com.javatechie.util.ImageUtils;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
// import org.springframework.web.multipart.MultipartFile;

 import java.io.File;
 import java.io.FileNotFoundException;
// import java.io.IOException;
//  import java.nio.file.Files;
 import java.util.Optional;

//  import com.javatechie.entity.FileData;
//  import com.javatechie.entity.ImageData;
//  import com.javatechie.respository.FileDataRepository;
//  import com.javatechie.respository.StorageRepository;
//  import com.javatechie.util.ImageUtils;
//  import org.springframework.beans.factory.annotation.Autowired;
//  import org.springframework.stereotype.Service;
//  import org.springframework.web.multipart.MultipartFile;
 
//  import java.io.File;
 import java.io.IOException;
//  import java.nio.file.Files;
//  import java.util.Optional;



@Service
public class CertificateService {

    @Autowired
    private CertificateRepository certificateRepository;


    private final String FOLDER_PATH="C:/Users/bishoy sameh/OneDrive/Desktop/myFiles";



    public String uploadImageToFileSystem(MultipartFile file, Long patientId,
                                        Long vaccinationCenterId, Long vaccineId) throws IOException {
        String filePath = FOLDER_PATH + file.getOriginalFilename();

        // Certificate certificate = new Certificate();
        User patient = new User();
        patient.setId(patientId);

        
        VaccinationCenter vaccinationCenter = new VaccinationCenter();
        vaccinationCenter.setId(vaccinationCenterId);

        Vaccine vaccine = new Vaccine();
        vaccine.setId(vaccineId);
       

        Certificate fileData=certificateRepository.save(Certificate.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .filePath(filePath)
                .patient(patient)
                .vaccinationCenter(vaccinationCenter)
                .vaccine(vaccine)
                .build());
                
                

        file.transferTo(new File(filePath));

        if (fileData != null) {
            return "file uploaded successfully : " + filePath;
        }
        return null;
    }






    public String uploadImageOnlyToFileSystem(MultipartFile file, Long patientId) throws IOException {
String filePath = FOLDER_PATH + file.getOriginalFilename();

// Certificate certificate = new Certificate();
User patient = new User();
patient.setId(patientId);


Certificate fileData=certificateRepository.save(Certificate.builder()
.name(file.getOriginalFilename())
.type(file.getContentType())
.filePath(filePath)
.patient(patient)
.build());



file.transferTo(new File(filePath));

if (fileData != null) {
return "file uploaded successfully : " + filePath;
}
return null;
}


        
            public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
                Optional<Certificate> fileData = certificateRepository.findByName(fileName);
                String filePath=fileData.get().getFilePath();
                byte[] images = Files.readAllBytes(new File(filePath).toPath());
                return images;
            }      

            
            public byte[] downloadImageFromFileSystemById(Long patientId) throws IOException {
        Optional<Certificate> fileData = certificateRepository.findByPatientId(patientId);
        if (fileData.isPresent()) {
            String filePath = fileData.get().getFilePath();
            byte[] images = Files.readAllBytes(new File(filePath).toPath());
            return images;
        } else {
            throw new FileNotFoundException("Certificate not found for patient with ID: " + patientId);
        }
    }

    }



            // public String uploadImageToFileSystem(MultipartFile file) 
    // try {
    //     // Save the uploaded file to a location on the server
    //     String fileName = StringUtils.cleanPath(certificateFile.getOriginalFilename());
    //     @SuppressWarnings("rawtypes")
    //     Path path = Paths.get("uploads").resolve(fileName);
    //     Files.copy(certificateFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

        // // Save the certificate details to the database
        // Certificate certificate = new Certificate();
        // certificate.setCertificateFile(fileName);

        // Set patient, vaccination center, and vaccine
        // User patient = new User();
        // patient.setId(patientId);

        // VaccinationCenter vaccinationCenter = new VaccinationCenter();
        // vaccinationCenter.setId(vaccinationCenterId);

        // Vaccine vaccine = new Vaccine();
        // vaccine.setId(vaccineId);

      

    //     var certificate = com.example.demo.certificate.Certificate.builder()
    //     .certificateFile(certificateFile)
    //     certificate.setPatient(patient);
    //     certificate.setVaccinationCenter(vaccinationCenter);
    //     certificate.setVaccine(vaccine);
    //     certificate.setType(fileName);

    //     certificateRepository.save(certificate);

    //         return ResponseEntity.ok("Certificate uploaded successfully!");
    //     } catch (IOException ex) {
    //         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
    //                 .body("Failed to upload certificate: " + ex.getMessage());
    //     }
    // }
    // }