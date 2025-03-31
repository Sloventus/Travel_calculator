package org.javaguru.travel.insurance.core.services;

import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.underwriting.TravelPremiumCalculationResult;
import org.javaguru.travel.insurance.core.underwriting.TravelPremiumUnderwriting;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class CalculatorRiskPremiumsForAllPersons {

    private final TravelPremiumUnderwriting premiumUnderwriting;


    public void calculate (AgreementDTO agreement) {
        agreement.getPersons().forEach(person -> {
            TravelPremiumCalculationResult calculationResult = premiumUnderwriting.calculatePremium(agreement, person);
            person.setRisks(calculationResult.risks());
        });
    }
}
