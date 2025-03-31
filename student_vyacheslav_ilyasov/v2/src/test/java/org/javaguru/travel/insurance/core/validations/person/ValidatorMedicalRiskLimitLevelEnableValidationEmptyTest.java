package org.javaguru.travel.insurance.core.validations.person;

import org.javaguru.travel.insurance.core.api.dto.PersonDTO;
import org.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidatorMedicalRiskLimitLevelEnableValidationEmptyTest {

    @InjectMocks
    private MedicalRiskLimitLevelEnableEmptyValidation validator;

    @Mock
    private ValidationErrorFactory validationErrorFactory;

    @Test
    void reutrnErrorWithNull() {
        PersonDTO personDTO = new PersonDTO();

        ValidationErrorDTO error = new ValidationErrorDTO("ERROR_CODE_9"
                ,"Field medicalRiskLimitLevel is empty!");

        when(validationErrorFactory.buildError("ERROR_CODE_9")).thenReturn(error);

        Optional<ValidationErrorDTO> result = validator.validate(personDTO);

        assertTrue(result.isPresent());
        assertEquals(error, result.get());
    }

    @Test
    void reutrnErrorWithEmpty() {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setMedicalRiskLimitLevel("");

        ValidationErrorDTO error = new ValidationErrorDTO("ERROR_CODE_9"
                ,"Field medicalRiskLimitLevel is empty!");

        when(validationErrorFactory.buildError("ERROR_CODE_9")).thenReturn(error);

        Optional<ValidationErrorDTO> result = validator.validate(personDTO);

        assertTrue(result.isPresent());
        assertEquals(error, result.get());
    }

    @Test
    public void returnNoError() {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setMedicalRiskLimitLevel("LEVEL_15000");

        Optional<ValidationErrorDTO> result = validator.validate(personDTO);

        assertFalse(result.isPresent());
    }
}