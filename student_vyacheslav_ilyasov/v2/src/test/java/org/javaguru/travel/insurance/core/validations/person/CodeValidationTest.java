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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CodeValidationTest {

    @Mock
    private ValidationErrorFactory validationErrorFactory;

    @InjectMocks
    private CodeValidation codeValidation;

    @Test
    void testValidateWithValidLastName() {
        PersonDTO request = new PersonDTO();
        request.setPersonCode("Doe");

        Optional<ValidationErrorDTO> result = codeValidation.validate(request);

        assertTrue(result.isEmpty(), "Ожидается, что ошибки не будет");
    }

    @Test
    void testValidateWithEmptyLastName() {
        PersonDTO request = new PersonDTO();
        request.setPersonCode("");

        when(validationErrorFactory.buildError("ERROR_CODE_11")).thenReturn(
                new ValidationErrorDTO(
                        "ERROR_CODE_11",
                        "Field personCode is empty!"));

        Optional<ValidationErrorDTO> result = codeValidation.validate(request);

        assertTrue(result.isPresent(), "Ожидается ошибка, так как фамилия пустая");
        assertEquals("ERROR_CODE_11", result.get().getErrorCode());
        assertEquals("Field personCode is empty!", result.get().getDescription());
    }

    @Test
    void testValidateWithNullLastName() {
        PersonDTO request = new PersonDTO();
        request.setPersonLastName(null);

        when(validationErrorFactory.buildError("ERROR_CODE_11")).thenReturn(
                new ValidationErrorDTO(
                        "ERROR_CODE_11",
                        "Field personCode is empty!"));

        Optional<ValidationErrorDTO> result = codeValidation.validate(request);

        assertTrue(result.isPresent(), "Ожидается ошибка, так как фамилия равна null");
        assertEquals("ERROR_CODE_11", result.get().getErrorCode());
        assertEquals("Field personCode is empty!", result.get().getDescription());
    }
}
