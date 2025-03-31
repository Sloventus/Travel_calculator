package org.javaguru.travel.insurance.core.underwriting.calculators.medical;

import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.api.dto.PersonDTO;
import org.javaguru.travel.insurance.core.repositories.AgeCoefficientRepository;
import org.javaguru.travel.insurance.core.util.DateTimeUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AgeCoefficientCalculatorTest {

    @Mock
    private AgeCoefficientRepository ageCoefficientRepository;

    @Mock
    private DateTimeUtil dateTimeUtil;

    @InjectMocks
    private AgeCoefficientCalculator calculator;

    @Test
    void getCoefficient_shouldReturnCoefficient_whenAgeCoefficientExists() {
        PersonDTO request = new PersonDTO();
        request.setPersonBirthDate(LocalDate.of(1990, 1, 1));

        when(dateTimeUtil.getYearByBirthday(request.getPersonBirthDate())).thenReturn(33L);
        when(ageCoefficientRepository.findCoefficientByAge(33L))
                .thenReturn(Optional.of(BigDecimal.valueOf(1.2)));

        ReflectionTestUtils.setField(calculator, "enableAgeCoefficient", true);

        BigDecimal result = calculator.getCoefficient(new AgreementDTO(), request);

        assertEquals(BigDecimal.valueOf(1.2), result);
        verify(dateTimeUtil).getYearByBirthday(request.getPersonBirthDate());
        verify(ageCoefficientRepository).findCoefficientByAge(33L);
    }

    @Test
    void getCoefficient_shouldThrowException_whenAgeCoefficientNotFound() {
        PersonDTO request = new PersonDTO();
        request.setPersonBirthDate(LocalDate.of(1990, 1, 1));

        when(dateTimeUtil.getYearByBirthday(request.getPersonBirthDate())).thenReturn(33L);
        when(ageCoefficientRepository.findCoefficientByAge(33L)).thenReturn(Optional.empty());

        ReflectionTestUtils.setField(calculator, "enableAgeCoefficient", true);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> calculator.getCoefficient(new AgreementDTO(), request));
        assertEquals("Age coefficient exception (age = 33)", exception.getMessage());
        verify(dateTimeUtil).getYearByBirthday(request.getPersonBirthDate());
        verify(ageCoefficientRepository).findCoefficientByAge(33L);
    }
}