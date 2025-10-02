package com.example.billink.Aspects;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Aspect
@Component
public class LogAspect {
    @Before("execution(* com.example.billink.Configuration.*.*(..))")
    public void logBeforeMethodExecution() {
        Instant instant = Instant.now();
        System.out.println("Starting listener action: " + instant);
    }
    @After("execution(* com.example.billink.Configuration.*.*(..))")
    public void logAfterMethodExecution() {
        Instant instant = Instant.now();
        System.out.println("Ending listener action"+ instant);
    }



}
