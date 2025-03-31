package org.javaguru.travel.insurance.core.underwriting;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.api.dto.PersonDTO;
import org.javaguru.travel.insurance.core.api.dto.RiskDTO;
import org.javaguru.travel.insurance.core.underwriting.calculators.TravelRiskPremiumCalculator;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class SelectedRisksPremiumCalculator {

    private final List<TravelRiskPremiumCalculator> travelRiskPremiumCalculatorList;

    public List<RiskDTO> getRisks(AgreementDTO agreement, PersonDTO person) {
        return travelRiskPremiumCalculatorList.stream()
                .filter(calc -> agreement.getSelectedRisks().contains(calc.getRiskIc()))
                .map(calc -> new RiskDTO(calc.getRiskIc(), calc.calculatePremium(agreement, person)))
                .toList();
    }
}
