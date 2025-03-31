package org.javaguru.travel.insurance.core.underwriting.calculators.medical;

import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.api.dto.PersonDTO;
import org.javaguru.travel.insurance.core.util.DateTimeUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DaysCoefficientCalculatorTest {

    @Mock
    private DateTimeUtil dateTimeUtil;

    @InjectMocks
    private DaysCoefficientCalculator calculator;

    @Test
    void getCoefficient_shouldReturnCorrectDaysCount() {
        AgreementDTO request = new AgreementDTO();
        request.setAgreementDateFrom(LocalDate.of(2023, 1, 1));
        request.setAgreementDateTo(LocalDate.of(2023, 1, 10));

        when(dateTimeUtil.calculateDaysBetweenDates(
                request.getAgreementDateFrom(),
                request.getAgreementDateTo()))
                .thenReturn(BigDecimal.valueOf(9));

        BigDecimal result = calculator.getCoefficient(request, new PersonDTO());

        assertEquals(BigDecimal.valueOf(9), result);
        verify(dateTimeUtil).calculateDaysBetweenDates(
                request.getAgreementDateFrom(),
                request.getAgreementDateTo());
    }
}