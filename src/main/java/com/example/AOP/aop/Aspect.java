package com.example.AOP.aop;

import com.example.AOP.entity.Employee;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@org.aspectj.lang.annotation.Aspect
@Component
public class Aspect {
    //before and after execution on controller and service layer
    //value ="" is the pointcut
    //.controller.* = joinpoint
    //method = advices
    Logger logger = LoggerFactory.getLogger(Aspect.class);

    @Before(value = "execution(* com.example.AOP.controller.*.*(..))")
    public void beforeAdvice(JoinPoint joinPoint) {
        logger.info("Request to Controller Layer " + joinPoint.getSignature());
    }

    @Before(value = "execution(* com.example.AOP.service.*.*(..))")
    public void beforeAdviceService(JoinPoint joinPoint) {
        logger.info("Request to Service Layer " + joinPoint.getSignature());
    }

    @After(value = "execution(* com.example.AOP.controller.*.*(..))")
    public void afterAdvice(JoinPoint joinPoint) {
        logger.info("After Execution Request to Controller Layer " + joinPoint.getSignature());
    }

    @After(value = "execution(* com.example.AOP.service.*.*(..))")
    public void afterAdviceService(JoinPoint joinPoint) {
        logger.info("After Execution Request to Service Layer " + joinPoint.getSignature());
    }

    @AfterReturning(value = "execution(* com.example.AOP.service.EmployeeService.saveEmployee(..))",
            returning = "employee")
    public void afterReturningForSaveEmployee(JoinPoint joinPoint, Employee employee) {
        logger.info("Business logic of save employee and the id is " + employee.getId());
    }

    @AfterThrowing(value = "execution(* com.example.AOP.service.EmployeeService.saveEmployee(..))",
            throwing = "exception")
    public void afterThrowingForSaveEmployee(JoinPoint joinPoint, Exception exception) {
        logger.info("Business logic of save employee throw exception " + exception);
    }

    //@around
    @Around(value = "execution(* com.example.AOP.service.EmployeeService.saveEmployee(..))")
    public Employee aroundAdviceForAddEmployeeService(ProceedingJoinPoint proceedingJoinPoint) {
        logger.info("Inside around advice in aspect : Business logic to save employee");
        try {
            return (Employee) proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            logger.info("Inside around advice in aspect : Business logic to not save employee ");
        }
        logger.info("Inside around advice in aspect : Business logic to save employee ended");
        return null;
    }
}
