package com.example.billink.Aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Aspect
@Component
public class LogControllerAspect {

    @Pointcut("execution(* com.example.billink.Controllers.*.*(..))")
    public void getControllerPointCut(){}

    /*@Before("getControllerPointCut()")
    public void logBeforeControllerExecution() {
        Instant instant = Instant.now();
        System.out.println("Starting controller action "+ instant);
    }
    @After("getControllerPointCut()")
    public void logAfterControllerExecution() {
        Instant instant = Instant.now();
        System.out.println("Ending controller action "+ instant);
    }*/

    @Around("getControllerPointCut()")
    public Object logAroundServiceExecution(ProceedingJoinPoint joinPoint) throws Throwable{
        Instant instant = Instant.now();
        System.out.println("Starting controller action "+ instant);
        Object  result = joinPoint.proceed();
        instant = Instant.now();
        System.out.println("Ending controller action "+ instant);
//object is the base super class, its helping us return the result, without it return a null response leading to a fatal error in graphql
        return result;
    }



}
