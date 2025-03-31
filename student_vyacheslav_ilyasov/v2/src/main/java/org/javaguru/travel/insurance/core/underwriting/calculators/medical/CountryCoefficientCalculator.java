package org.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.api.dto.PersonDTO;
import org.javaguru.travel.insurance.core.repositories.CountryDefaultDayRateRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class CountryCoefficientCalculator implements CoefficientCalculator {

    private final CountryDefaultDayRateRepository countryDefaultDayRateRepository;

    @Override
    public BigDecimal getCoefficient(AgreementDTO agreement, PersonDTO person) {
        return countryDefaultDayRateRepository.findDayRateByCountry(agreement.getCountry().toUpperCase())
                .orElseThrow(() -> new RuntimeException("Country coefficient exception."));
    }
}
