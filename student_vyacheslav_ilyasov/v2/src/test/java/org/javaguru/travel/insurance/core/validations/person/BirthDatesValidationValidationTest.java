package org.javaguru.travel.insurance.core.validations.person;

import org.javaguru.travel.insurance.core.api.dto.PersonDTO;
import org.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.javaguru.travel.insurance.core.util.DateTimeUtil;
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
class BirthDatesValidationValidationTest {

    @Mock
    private DateTimeUtil dateTimeUtil;

    @Mock
    private ValidationErrorFactory validationErrorFactory;

    @InjectMocks
    private BirthDatesValidation validator;

    @Test
    void testValidate_BirthDateIsNull() {
        PersonDTO request = new PersonDTO();
        request.setPersonBirthDate(null);

        when(validationErrorFactory.buildError("ERROR_CODE_8"))
                .thenReturn(new ValidationErrorDTO("ERROR_CODE_8"
                        , "Field birthDate incorrectly!"));

        Optional<ValidationErrorDTO> error = validator.validate(request);

        assertTrue(error.isPresent());
        assertEquals("ERROR_CODE_8", error.get().getErrorCode());
    }

    @Test
    void testValidate_BirthDateIsValid_AgeGreaterThanZero() {
        PersonDTO request = new PersonDTO();
        request.setPersonBirthDate(LocalDate.of(1990,1,1));

        when(dateTimeUtil.getTimeByBirthday(LocalDate.of(1990,1,1))).thenReturn(33L);

        Optional<ValidationErrorDTO> error = validator.validate(request);

        assertTrue(error.isEmpty());
    }

    @Test
    void testValidate_BirthDateIsValid_AgeLessOrEqualToZero() {
        PersonDTO request = new PersonDTO();
        request.setPersonBirthDate(LocalDate.of(2990,1,1));

        when(dateTimeUtil.getTimeByBirthday(LocalDate.of(2990, 1, 1))).thenReturn(0L);

        when(validationErrorFactory.buildError("ERROR_CODE_8"))
                .thenReturn(new ValidationErrorDTO("ERROR_CODE_8"
                        , "Field birthDate incorrectly!"));

        Optional<ValidationErrorDTO> error = validator.validate(request);

        assertTrue(error.isPresent());
        assertEquals("ERROR_CODE_8", error.get().getErrorCode());
    }
}