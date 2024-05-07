package com.example.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class LoggingAspect {


    @After("execution(* com.example.demo.vaccine..*(..))")

    public void logMethodAndArgs(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getSignature().getDeclaringTypeName();
        Object[] args = joinPoint.getArgs();
        
        System.out.println("Method Name: " + methodName);
        System.out.println("Class Name: " + className);
        System.out.println("Arguments: ");
        for (int i = 0; i < args.length; i++) {
            System.out.println("Arg " + (i + 1) + ": " + args[i]);
        }
    }
}
