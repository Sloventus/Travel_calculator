package org.javaguru.travel.insurance.core.underwriting.calculators;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.api.dto.PersonDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class TravelRiskCancellationPremiumCalculator implements TravelRiskPremiumCalculator {



    @Override
    public BigDecimal calculatePremium(AgreementDTO agreement, PersonDTO person) {
        return BigDecimal.ONE;
    }

    @Override
    public String getRiskIc() {
        return "TRAVEL_CANCELLATION";

    }
}
