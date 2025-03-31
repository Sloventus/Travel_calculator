package org.javaguru.travel.insurance.core.underwriting;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.api.dto.PersonDTO;
import org.javaguru.travel.insurance.core.api.dto.RiskDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class TravelPremiumUnderwritingImpl implements TravelPremiumUnderwriting {

    private final SelectedRisksPremiumCalculator selectedRisksPremiumCalculator;


    @Override
    public TravelPremiumCalculationResult calculatePremium(AgreementDTO agreement, PersonDTO person) {
        return new TravelPremiumCalculationResult(agreementPremium(agreement, person),getRisks(agreement, person));
    }

    private BigDecimal agreementPremium(AgreementDTO agreement, PersonDTO person) {
        return getRisks(agreement, person).stream()
                .map(RiskDTO::getPremium)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private List<RiskDTO> getRisks(AgreementDTO agreement, PersonDTO request) {
        return selectedRisksPremiumCalculator.getRisks(agreement, request);
    }

}
