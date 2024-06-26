package com.example.demo.aspect;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MeaurmentAspect {
	
	
	Logger log = LoggerFactory.getLogger(MeaurmentAspect.class);

	@Around(value = "execution(* com.example.demo.vaccinationCenter..* (..))")

	public Object logTime(ProceedingJoinPoint  joinPoint) throws Throwable {
		
		long startTime = System.currentTimeMillis();
		StringBuilder sb = new StringBuilder("performance ");
		sb.append("\tfor: ").append(joinPoint.getSignature())
				.append("\twithArgs: ").append("(").append(StringUtils.join(joinPoint.getArgs(), ",")).append(")");
		sb.append("\ttook: ");
		Object returnValue = joinPoint.proceed();
		log.info(sb.append(System.currentTimeMillis() - startTime).append(" ms.").toString());
		
		return returnValue;
	}





}