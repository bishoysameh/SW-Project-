// package com.example.demo.vaccinationCenter;

// // import java.time.LocalDate;
// // import java.time.Month;
// import java.util.List;

// import org.springframework.boot.CommandLineRunner;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// @Configuration
// public class VaccinationCenterConfig {
//     @SuppressWarnings("null")
//     @Bean
//     CommandLineRunner commandLineRunner (VaccinationCenterRepository repository){
//         return args ->{
//             VaccinationCenter vaccinationCenterOne =  new VaccinationCenter(
//                                             "vaccinationCenterOne",
//                                             "address 1",
//                                             "contact_info 1"
//                                     );
//             VaccinationCenter vaccinationCenterTwo =  new VaccinationCenter(
//                                             "vaccinationCenterTwo",
//                                             "address 1",
//                                             "contact_info 1"
//                                     );
          
//              repository.saveAll( List.of (vaccinationCenterOne,vaccinationCenterTwo));                       
//         };
//     }
// }
