package org.javaguru.travel.insurance.core.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreResult;
import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class ResponseFactory {

    private final CalculatorRiskPremiumsForAllPersons calculatorRiskPremiumsForAllPersons;
    private final CalculatorTotalAgreementPremium calculatorTotalAgreementPremium;


    public TravelCalculatePremiumCoreResult buildResponse(List<ValidationErrorDTO> errors) {
        return new TravelCalculatePremiumCoreResult(errors);
    }

    public TravelCalculatePremiumCoreResult buildResponse(AgreementDTO agreement) {
        calculatorRiskPremiumsForAllPersons.calculate(agreement);
        agreement.setAgreementPremium(calculatorTotalAgreementPremium.calculate(agreement));
        return new TravelCalculatePremiumCoreResult(agreement);
    }
}
