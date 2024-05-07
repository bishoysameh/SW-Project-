package com.example.demo.auth;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

// import javax.management.relation.Role;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.config.JwtService;
import com.example.demo.user.User;
// import com.example.demo.user.Role;
import com.example.demo.user.UserRepositry;

import jakarta.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor





public class AuthenticationService {
  private final PasswordEncoder passwordEncoder; 
  private final UserRepositry repository;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;
 
     @SuppressWarnings("null")
     public AuthenticationResponse register(RegisterRequest request) {
      System.out.println(request.getRole().toString().equals("VACCINATION_CENTER"));
    var user = com.example.demo.user.User.builder()
      // .firstName(request.getFirstname())
      // .lastName(request.getLastname())
      .username(request.getUsername())
      .email(request.getEmail())
      .password(passwordEncoder.encode(request.getPassword()))
      .role(request.getRole())
      .status(request.getRole().toString().equals("VACCINATION_CENTER") || request.getRole().toString().equals("ADMIN")  ? "Accepted" : "Pending")

      .build();
          repository.save(user);
          if (user.getRole().toString().equals("VACCINATION_CENTER") || user.getRole().toString().equals("ADMIN") ) {
            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder().token(jwtToken).build();
        } else {
            return AuthenticationResponse.builder().message("Registration pending approval").build();
        }
     }
 
 




     public void approveUserRegistration(int userId) {
      Optional<User> userOptional = repository.findById(userId);
      if (userOptional.isPresent()) {
          User user = userOptional.get();
          user.setStatus("Accepted");
          repository.save(user);
      } else {
          throw new EntityNotFoundException("User not found with ID: " + userId);
      }
  }

// public class AuthenticationService {
//  private final PasswordEncoder passwordEncoder; 
//  private final UserRepositry repository;
//  private final JwtService jwtService;
//  private final AuthenticationManager authenticationManager;

//     @SuppressWarnings("null")
//     public AuthenticationResponse register(RegisterRequest request) {

//    var user = com.example.demo.user.User.builder()
//      .firstName(request.getFirstname())
//      .lastName(request.getLastname())
//      .email(request.getEmail())
//      .password(passwordEncoder.encode(request.getPassword()))
//      .role(request.getRole())
//      .build();
//          repository.save(user);
//      var jwtToken = jwtService.generateToken(user);
//      return AuthenticationResponse.builder().token(jwtToken).build();
//     }


    
    public AuthenticationResponse authenticate(authenticationRequest request) {
        authenticationManager.authenticate( new UsernamePasswordAuthenticationToken (
          request.getEmail(),
          request.getPassword()
        )
    );
    var user = repository.findByEmail(request.getEmail()).orElseThrow();
    if(user.getStatus().toString().equals("Accepted"))
    {
    var jwtToken = jwtService.generateToken(user);
    return AuthenticationResponse.builder().token(jwtToken).build();
    }
    else
    return AuthenticationResponse.builder().message("sorry, you cant login untill admin is accept registeration").build();


    }



}
