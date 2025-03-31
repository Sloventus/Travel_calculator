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
class FirstNameValidationTest {

    @Mock
    private ValidationErrorFactory validationErrorFactory;

    @InjectMocks
    private FirstNameValidation firstNameValidation;

    @Test
    void testValidateWithValidFirstName() {
        PersonDTO request = new PersonDTO();
        request.setPersonFirstName("John");

        Optional<ValidationErrorDTO> result = firstNameValidation.validate(request);

        assertTrue(result.isEmpty(), "Ожидается, что ошибки не будет");
    }

    @Test
    void testValidateWithEmptyFirstName() {
        PersonDTO request = new PersonDTO();
        request.setPersonFirstName("");

        when(validationErrorFactory.buildError("ERROR_CODE_1")).thenReturn(
                new ValidationErrorDTO(
                        "ERROR_CODE_1",
                        "Field personFirstName is empty!"));

        Optional<ValidationErrorDTO> result = firstNameValidation.validate(request);

        assertTrue(result.isPresent(), "Ожидается ошибка, так как имя пустое");
        assertEquals("ERROR_CODE_1", result.get().getErrorCode());
        assertEquals("Field personFirstName is empty!", result.get().getDescription());
    }

    @Test
    void testValidateWithNullFirstName() {
        PersonDTO request = new PersonDTO();
        request.setPersonFirstName(null);

        when(validationErrorFactory.buildError("ERROR_CODE_1")).thenReturn(
                new ValidationErrorDTO("ERROR_CODE_1","Field personFirstName is empty!"));

        Optional<ValidationErrorDTO> result = firstNameValidation.validate(request);

        assertTrue(result.isPresent(), "Ожидается ошибка, так как имя равно null");
        assertEquals("ERROR_CODE_1", result.get().getErrorCode());
        assertEquals("Field personFirstName is empty!", result.get().getDescription());
    }
}

