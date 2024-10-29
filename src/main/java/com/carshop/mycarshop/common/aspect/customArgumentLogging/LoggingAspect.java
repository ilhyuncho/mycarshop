package com.carshop.mycarshop.common.aspect.customArgumentLogging;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Log4j2
@Component
@Order(1)
public class LoggingAspect {

    @Around("@annotation(ElapseLoggable)")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable{

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        log.error("(1) start time clock");

        Object result;

        try {
            result = joinPoint.proceed();
        } finally {
            stopWatch.stop();
            String methodName = joinPoint.getSignature().getName();
            long elapsedTime = stopWatch.getLastTaskTimeMillis();
            log.error("(1) {}, elapsed time: {} ms", methodName, elapsedTime);

            //getProduct, elapsed time: 112 ms
        }

        return result;
    }

}