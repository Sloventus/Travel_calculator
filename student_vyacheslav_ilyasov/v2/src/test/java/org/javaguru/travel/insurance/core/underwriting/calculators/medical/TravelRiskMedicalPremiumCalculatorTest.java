package org.javaguru.travel.insurance.core.underwriting.calculators.medical;

import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.api.dto.PersonDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TravelRiskMedicalPremiumCalculatorTest {


    @Mock
    private CoefficientCalculator coefficientCalculator1;

    @Mock
    private CoefficientCalculator coefficientCalculator2;

    @InjectMocks
    private TravelRiskMedicalPremiumCalculator calculator;

    @Test
    void calculatePremium_shouldReturnCorrectPremium() {
        AgreementDTO agreement = new AgreementDTO();
        PersonDTO person = new PersonDTO();

        when(coefficientCalculator1.getCoefficient(agreement, person)).thenReturn(BigDecimal.valueOf(1.2));
        when(coefficientCalculator2.getCoefficient(agreement, person)).thenReturn(BigDecimal.valueOf(50));
        calculator = new TravelRiskMedicalPremiumCalculator(
                List.of(coefficientCalculator1, coefficientCalculator2)
        );

        BigDecimal result = calculator.calculatePremium(agreement, person);

        assertEquals(BigDecimal.valueOf(60.0), result); // 1.2 * 50 = 60
        verify(coefficientCalculator1).getCoefficient(agreement, person);
        verify(coefficientCalculator2).getCoefficient(agreement, person);
    }
}
