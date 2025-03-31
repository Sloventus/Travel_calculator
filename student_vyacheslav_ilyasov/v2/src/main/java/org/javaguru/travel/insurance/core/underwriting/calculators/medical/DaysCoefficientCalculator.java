package org.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.api.dto.PersonDTO;
import org.javaguru.travel.insurance.core.util.DateTimeUtil;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class DaysCoefficientCalculator implements CoefficientCalculator {

    private final DateTimeUtil dateTimeUtil;

    @Override
    public BigDecimal getCoefficient(AgreementDTO agreement, PersonDTO person) {
        return dateTimeUtil.calculateDaysBetweenDates(
                agreement.getAgreementDateFrom()
                , agreement.getAgreementDateTo());

    }
}
