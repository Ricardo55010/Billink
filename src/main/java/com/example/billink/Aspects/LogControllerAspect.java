package com.example.billink.Aspects;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Aspect
@Component
public class LogControllerAspect {


    @Before("execution(* com.example.billink.Controllers.*.*(..))")
    public void logBeforeControllerExecution() {
        Instant instant = Instant.now();
        System.out.println("Starting controller action "+ instant);
    }
    @After("execution(* com.example.billink.Controllers.*.*(..))")
    public void logAfterControllerExecution() {
        Instant instant = Instant.now();
        System.out.println("Ending controller action "+ instant);
    }



}
