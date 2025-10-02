package com.example.billink.Aspects;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Aspect
@Component
public class LogServiceAspect {

    @Before("execution(* com.example.billink.Services.*.*(..))")
    public void logBeforeServiceExecution() {
        Instant instant = Instant.now();
        System.out.println("Starting service action "+ instant);
    }
    @After("execution(* com.example.billink.Services.*.*(..))")
    public void logAfterServiceExecution() {
        Instant instant = Instant.now();
        System.out.println("Ending service action "+ instant);
    }


}
