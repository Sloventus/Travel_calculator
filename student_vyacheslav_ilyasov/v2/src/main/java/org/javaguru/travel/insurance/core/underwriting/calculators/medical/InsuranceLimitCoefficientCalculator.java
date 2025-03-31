package org.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.api.dto.PersonDTO;
import org.javaguru.travel.insurance.core.repositories.MedicalRiskLimitLevelRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class InsuranceLimitCoefficientCalculator implements CoefficientCalculator {

    private final MedicalRiskLimitLevelRepository medicalRiskLimitLevelRepository;

    @Value("${medical.risk.limit.level.enabled:false}")
    private boolean medicalRiskLimitLevelEnabled;

    @Override
    public BigDecimal getCoefficient(AgreementDTO agreement, PersonDTO person) {
        return medicalRiskLimitLevelEnabled
                ? medicalRiskLimitLevelRepository
                    .findByMedicalRiskLimitLevel(person.getMedicalRiskLimitLevel())
                    .get()
                    .getCoefficient()
                : BigDecimal.ONE;
    }
}
