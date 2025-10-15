package com.example.billink.Aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Aspect
@Component
public class LogServiceAspect {


    @Pointcut("execution(* com.example.billink.Services.*.*(..))")
    public void getServicePointCut(){}

    /*@Before("getServicePointCut()")
    public void logBeforeServiceExecution() {
        Instant instant = Instant.now();
        System.out.println("Starting service action "+ instant);
    }
    @After("getServicePointCut()")
    public void logAfterServiceExecution() {
        Instant instant = Instant.now();
        System.out.println("Ending service action "+ instant);
    }*/

    @Around("getServicePointCut()")
    public Object logAroundServiceExecution(ProceedingJoinPoint joinPoint) throws Throwable{
        Instant instant = Instant.now();
        System.out.println("Starting service action "+ instant);
        Object  result = joinPoint.proceed();
        instant = Instant.now();
        System.out.println("Ending service action "+ instant);
//object is the base super class, its helping us return the result, without it return a null response leading to a fatal error in graphql
        return result;
    }


}
