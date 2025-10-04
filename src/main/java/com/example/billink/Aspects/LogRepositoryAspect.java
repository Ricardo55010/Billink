package com.example.billink.Aspects;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Aspect
@Component
public class LogRepositoryAspect {

    @Pointcut("execution(* com.example.billink.Repository.*.*(..))")
    public void getRepositoryPointCut(){}

    @Before("getRepositoryPointCut()")
    public void logBeforeRepositoryExecution() {
        Instant instant = Instant.now();
        System.out.println("Starting repository action "+ instant);
    }

    @After("getRepositoryPointCut()")
    public void logAfterRepositoryxecution() {
        Instant instant = Instant.now();
        System.out.println("Ending repository action "+ instant);
    }

}
