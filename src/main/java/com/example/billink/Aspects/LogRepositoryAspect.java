package com.example.billink.Aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Aspect
@Component
public class LogRepositoryAspect {

    @Pointcut("execution(* com.example.billink.Repository.*.*(..))")
    public void getRepositoryPointCut(){}

    /* @Before("getRepositoryPointCut()")
     public void logBeforeRepositoryExecution() {
         Instant instant = Instant.now();
         System.out.println("Starting repository action "+ instant);
     }

     @After("getRepositoryPointCut()")
     public void logAfterRepositoryxecution() {
         Instant instant = Instant.now();
         System.out.println("Ending repository action "+ instant);
     }*/
    @Around("getRepositoryPointCut()")
    public Object logAroundServiceExecution(ProceedingJoinPoint joinPoint) throws Throwable{
        Instant instant = Instant.now();
        System.out.println("Starting repository action "+ instant);
        Object  result = joinPoint.proceed();
        instant = Instant.now();
        System.out.println("Ending repository action "+ instant);
//object is the base super class, its helping us return the result, without it return a null response leading to a fatal error in graphql
        return result;
    }

}
