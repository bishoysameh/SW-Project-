package com.example.demo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class BeforeMethod {
    @Before("execution(*  com.example.demo.vaccinationCenter.VaccinationCenterService.getVaccinationCenters())")
    public void logBeforeDoSomething() {
        System.out.println("Logging: before get vaccination center method execute");
    }
}
