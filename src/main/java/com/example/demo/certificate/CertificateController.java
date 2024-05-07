package com.example.demo.certificate;

// import org.hibernate.engine.jdbc.env.internal.LobCreationLogging_.logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("Certificate")

public class CertificateController {

    @Autowired
    private CertificateService certificateService;

    @PostMapping("/Upload")
    public ResponseEntity<?> uploadImageToFileSystem(
        
                                                    @RequestParam("file") MultipartFile file,
                                                    @RequestParam("patientId") Long patientId,
                                                    @RequestParam("vaccinationCenterId") Long vaccinationCenterId,
                                                    @RequestParam("vaccineId") Long vaccineId) {
       try{
            String uploadImage = certificateService.uploadImageToFileSystem(file , patientId , vaccinationCenterId , vaccineId);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(uploadImage);
           }
           catch (IOException e) {
            // Handle the exception (e.g., log the error, display an error message)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }





    @PostMapping("/Upload/File")
    public ResponseEntity<?> uploadImageOnlyToFileSystem(
        
                                                    @RequestParam("file") MultipartFile file,
                                                    @RequestParam("patientId") Long patientId)
                                                  {
       try{
            String uploadImage = certificateService.uploadImageOnlyToFileSystem(file , patientId );
            return ResponseEntity.status(HttpStatus.OK)
                    .body(uploadImage);
           }
           catch (IOException e) {
            // Handle the exception (e.g., log the error, display an error message)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }







    @GetMapping("/fileSystem/{fileName}")
    public ResponseEntity<?> downloadImageFromFileSystem(@PathVariable String fileName) throws IOException {
        byte[] imageData=certificateService.downloadImageFromFileSystem(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);

    }






    @GetMapping("/fileSystem/ById/{patientId}")
    public ResponseEntity<?> downloadImageFromFileSystemById(@PathVariable Long patientId) throws IOException {
        byte[] imageData = certificateService.downloadImageFromFileSystemById(patientId);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }

    
    


    // @GetMapping
    // public ResponseEntity<?> getALlCertificates() throws IOException {
    //     byte[] imageData=certificateService.getALlCertificates();
    //     return ResponseEntity.status(HttpStatus.OK)
    //             .contentType(MediaType.valueOf("image/png"))
    //             .body(imageData);

    // }  
}