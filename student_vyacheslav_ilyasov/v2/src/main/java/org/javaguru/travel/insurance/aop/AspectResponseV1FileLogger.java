package org.javaguru.travel.insurance.aop;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumResponseV1;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class AspectResponseV1FileLogger extends PointcutParent {

    private final FileLoggerRequestResponse fileLoggerRequestResponse;

    @AfterReturning(value = "restV1ControllerMethod()"
            , returning = "response")
    public void logResponse(TravelCalculatePremiumResponseV1 response) {
        fileLoggerRequestResponse.logging(response);
    }
}
