package org.javaguru.travel.insurance.aop;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class AspectRequestV1FileLogger extends PointcutParent {

    private final FileLoggerRequestResponse fileLoggerRequestResponse;

    @Before("restV1ControllerMethod()")
    public void logRequest(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        TravelCalculatePremiumRequestV1 request = (TravelCalculatePremiumRequestV1) args[0];
        fileLoggerRequestResponse.logging(request);
    }

}
