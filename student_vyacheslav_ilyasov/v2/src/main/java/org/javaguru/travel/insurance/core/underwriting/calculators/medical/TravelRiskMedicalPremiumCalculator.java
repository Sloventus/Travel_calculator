package org.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.api.dto.PersonDTO;
import org.javaguru.travel.insurance.core.underwriting.calculators.TravelRiskPremiumCalculator;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class TravelRiskMedicalPremiumCalculator implements TravelRiskPremiumCalculator {

    private final List<CoefficientCalculator> coefficientCalculators;

    @Override
    public BigDecimal calculatePremium(AgreementDTO agreement, PersonDTO person) {
        return coefficientCalculators.stream()
                .map(calc -> calc.getCoefficient(agreement, person))
                .reduce(BigDecimal.ONE,(acc,coeff) -> acc.multiply(coeff));
    }

    @Override
    public String getRiskIc() {
        return "TRAVEL_MEDICAL";
    }
}
