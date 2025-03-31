package org.javaguru.travel.insurance.aop;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
@RequiredArgsConstructor
@Log4j2
public class AspectTimeToResponseLogger extends PointcutParent{


    @Around("restV1ControllerMethod() || restV2ControllerMethod()")
    public Object calculatePremium(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Object result = joinPoint.proceed();

        stopWatch.stop();
        log.info("Request processing time (ms):{}", stopWatch.getTotalTimeMillis());
        return result;
    }
}
