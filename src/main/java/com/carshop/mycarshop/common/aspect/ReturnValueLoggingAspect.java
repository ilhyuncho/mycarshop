package com.carshop.mycarshop.common.aspect;

import com.carshop.mycarshop.dto.PageResponseDTO;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Log4j2
public class ReturnValueLoggingAspect {
//    @AfterReturning(pointcut = "execution(* com.example.cih.service..*.*(..))", returning = "retVals")
//    public void printReturnObject(JoinPoint joinPoint, PageResponseDTO<?> retVals) throws Throwable{
//        log.error( "Service ReturnObject : " + retVals.toString());
//    }
//
//    @AfterThrowing(pointcut = "execution(* com.example.cih.service..*.*(..))", throwing="th")
//    public void printThrowable(JoinPoint joinPoint, Throwable th) throws Throwable {
//        log.error("Service Error message: {}", th.getMessage());
//    }

    // RestController 에서 리턴시 적용
    @AfterReturning(pointcut = "execution(* com.carshop.mycarshop.controller..*.*(..))", returning = "retVals")
    public void printControllerReturnObject(JoinPoint joinPoint, PageResponseDTO<?> retVals) throws Throwable{
        log.error( "3.Controller ReturnObject : " + retVals.toString());
    }

    @AfterThrowing(pointcut = "execution(* com.carshop.mycarshop.controller..*.*(..))", throwing="th")
    public void printControllerThrowable(JoinPoint joinPoint, Throwable th) throws Throwable {
        log.error("3.Controller Error message: {}", th.getMessage());
    }
}
