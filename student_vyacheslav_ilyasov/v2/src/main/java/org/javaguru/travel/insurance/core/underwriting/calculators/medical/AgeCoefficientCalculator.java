package org.javaguru.travel.insurance.core.underwriting.calculators.medical;


import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.api.dto.PersonDTO;
import org.javaguru.travel.insurance.core.repositories.AgeCoefficientRepository;
import org.javaguru.travel.insurance.core.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class AgeCoefficientCalculator implements CoefficientCalculator {

    private final AgeCoefficientRepository ageCoefficientRepository;
    private final DateTimeUtil dateTimeUtil;

    @Value("${age.coeficient.enable:true}")
    private boolean enableAgeCoefficient;

    @Override
    public BigDecimal getCoefficient(AgreementDTO agreement, PersonDTO person) {
            Long age = dateTimeUtil.getYearByBirthday(person.getPersonBirthDate());
            return enableAgeCoefficient ?
                    ageCoefficientRepository.findCoefficientByAge(age)
                    .orElseThrow(() -> new RuntimeException("Age coefficient exception (age = " + age + ")"))
                    : BigDecimal.ONE;
    }
}
