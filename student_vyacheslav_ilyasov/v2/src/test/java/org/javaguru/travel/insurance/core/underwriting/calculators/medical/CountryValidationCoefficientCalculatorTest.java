package org.javaguru.travel.insurance.core.underwriting.calculators.medical;

import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.api.dto.PersonDTO;
import org.javaguru.travel.insurance.core.repositories.CountryDefaultDayRateRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CountryValidationCoefficientCalculatorTest {

    @Mock
    private CountryDefaultDayRateRepository countryDefaultDayRateRepository;

    @InjectMocks
    private CountryCoefficientCalculator calculator;

    @Test
    void getCoefficient_shouldReturnCoefficient_whenCountryCoefficientExists() {
        AgreementDTO request = new AgreementDTO();
        request.setCountry("US");

        when(countryDefaultDayRateRepository.findDayRateByCountry("US"))
                .thenReturn(Optional.of(BigDecimal.valueOf(50)));

        BigDecimal result = calculator.getCoefficient(request, new PersonDTO());

        assertEquals(BigDecimal.valueOf(50), result);
        verify(countryDefaultDayRateRepository).findDayRateByCountry("US");
    }

    @Test
    void getCoefficient_shouldThrowException_whenCountryCoefficientNotFound() {
        AgreementDTO request = new AgreementDTO();
        request.setCountry("US");

        when(countryDefaultDayRateRepository.findDayRateByCountry("US"))
                .thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> calculator.getCoefficient(request,new PersonDTO()));
        assertEquals("Country coefficient exception.", exception.getMessage());
        verify(countryDefaultDayRateRepository).findDayRateByCountry("US");
    }
}