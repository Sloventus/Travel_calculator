package org.javaguru.travel.insurance.core.validations.agreement;

import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidatorDatesValidationTest {

    @Mock
    private ValidationErrorFactory validationErrorFactory;

    @InjectMocks
    private DatesValidation validatorDatesValidation;

    @Test
    void testValidateWithCorrectDates() {
        AgreementDTO request = new AgreementDTO();
        request.setAgreementDateFrom(LocalDate.of(2023,1,1)); // 01 Jan 2023
        request.setAgreementDateTo(LocalDate.of(2023,1,2));   // 02 Jan 2023

        Optional<ValidationErrorDTO> result = validatorDatesValidation.validate(request);

        assertTrue(result.isEmpty());
    }

    @Test
    void testValidateWithNullDates() {
        AgreementDTO request = new AgreementDTO();
        request.setAgreementDateFrom(null);
        request.setAgreementDateTo(null);

        when(validationErrorFactory.buildError("ERROR_CODE_3")).thenReturn(
                new ValidationErrorDTO(
                        "ERROR_CODE_3",
                        "Field dates incorrectly!"));

        Optional<ValidationErrorDTO> result = validatorDatesValidation.validate(request);

        assertTrue(result.isPresent());
        assertEquals("ERROR_CODE_3", result.get().getErrorCode());
        assertEquals("Field dates incorrectly!", result.get().getDescription());
    }

    @Test
    void testValidateWithInvalidDates() {
        AgreementDTO request = new AgreementDTO();

        request.setAgreementDateTo(LocalDate.of(2023,1,2));   // 02 Jan 2023
        request.setAgreementDateFrom(LocalDate.of(2024,1,1));

        when(validationErrorFactory.buildError("ERROR_CODE_3")).thenReturn(
                new ValidationErrorDTO(
                        "ERROR_CODE_3",
                        "Field dates incorrectly!"));

        Optional<ValidationErrorDTO> result = validatorDatesValidation.validate(request);

        assertTrue(result.isPresent());
        assertEquals("ERROR_CODE_3", result.get().getErrorCode());
        assertEquals("Field dates incorrectly!", result.get().getDescription());
    }
}
