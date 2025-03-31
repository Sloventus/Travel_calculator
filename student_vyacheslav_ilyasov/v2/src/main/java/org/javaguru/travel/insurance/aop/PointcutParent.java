package org.javaguru.travel.insurance.aop;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
public class PointcutParent {

    @Pointcut("execution(* org.javaguru.travel.insurance.rest.v1.TravelCalculatePremiumControllerV1.calculatePremium(..))")
    public void restV1ControllerMethod() {}

    @Pointcut("execution(* org.javaguru.travel.insurance.rest.v2.TravelCalculatePremiumControllerV2.calculatePremium(..))")
    public void restV2ControllerMethod() {}

    @Pointcut("execution(* org.javaguru.travel.insurance.web.v1.TravelInsuranceController.*(..))")
    public void webControllerMethod() {}

}
