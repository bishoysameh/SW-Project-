package com.example.demo.config;


// import jakarta.annotation.Nonnull;
// import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
// import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
// import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
// import static org.springframework.http.HttpMethod.DELETE;
// import static org.springframework.http.HttpMethod.GET;
// import static org.springframework.http.HttpMethod.POST;
// import static org.springframework.http.HttpMethod.PUT;

// import java.net.http.HttpRequest;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
// @EnableMethodSecurity
// @EnableMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class SecurityConfiguration  {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private  final  AuthenticationProvider authenticationProvider;



    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
    http
                  .csrf((e)->e.disable())
                  .authorizeHttpRequests( authorizeRequest ->
                          authorizeRequest
                                  .requestMatchers("api/v1/auth/**").permitAll()
                                   .requestMatchers("Certificate/**").permitAll()

                                  .anyRequest()
                                  .authenticated()

                                 )


                 .sessionManagement((session) -> session
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                       )


                  .authenticationProvider(authenticationProvider)
                  .addFilterBefore(jwtAuthFilter , UsernamePasswordAuthenticationFilter.class);




return http.build();
}
}
