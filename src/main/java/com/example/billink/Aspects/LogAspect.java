package com.example.billink.Aspects;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {
    @Before("execution(* com.example.user.Configuration.*.*(..))")
    public void logBeforeMethodExecution() {
        System.out.println("Starting listener action");
    }
    @After("execution(* com.example.user.Configuration.*.*(..))")
    public void logAfterMethodExecution() {
        System.out.println("Ending listener action");
    }
}
