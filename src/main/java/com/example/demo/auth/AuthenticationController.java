package com.example.demo.auth;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    // public AuthenticationController(AuthenticationService service) {
    //     this.service = service;
    // }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ){
      return ResponseEntity.ok(service.register(request));
         }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody authenticationRequest request
    ){
        return ResponseEntity.ok(service.authenticate(request));

    }



    @PutMapping("/{userId}/approve")
    @PreAuthorize("hasAuthority('ADMIN')")

    public ResponseEntity<Void> approveUserRegistration(@PathVariable int userId) {
        service.approveUserRegistration(userId);
        return ResponseEntity.ok().build();
    }

}
