package org.javaguru.travel.insurance.core.validations.agreement;

import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
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
class ValidatorCountryValidationNotEmptyTest {

    @Mock
    private ValidationErrorFactory validationErrorFactory;

    @InjectMocks
    private CountryNotEmpty validatorCountryNotEmpty;

    @Test
    void testValidateWithValidCountry() {
        AgreementDTO request = new AgreementDTO();
        request.setCountry("Japan");

        Optional<ValidationErrorDTO> result = validatorCountryNotEmpty.validate(request);

        assertTrue(result.isEmpty(), "Ожидается, что ошибки не будет");
    }

    @Test
    void testValidateWithEmptyCountry() {
        AgreementDTO request = new AgreementDTO();
        request.setCountry("");

        when(validationErrorFactory.buildError("ERROR_CODE_6")).thenReturn(
                new ValidationErrorDTO(
                        "ERROR_CODE_6",
                        "Field country is empty!"));

        Optional<ValidationErrorDTO> result = validatorCountryNotEmpty.validate(request);

        assertTrue(result.isPresent(), "Ожидается ошибка, так как фамилия пустая");
        assertEquals("ERROR_CODE_6", result.get().getErrorCode());
        assertEquals("Field country is empty!", result.get().getDescription());
    }

    @Test
    void testValidateWithNullCountry() {
        AgreementDTO request = new AgreementDTO();
        request.setCountry(null);

        when(validationErrorFactory.buildError("ERROR_CODE_6")).thenReturn(
                new ValidationErrorDTO(
                        "ERROR_CODE_6",
                        "Field country is empty!"));

        Optional<ValidationErrorDTO> result = validatorCountryNotEmpty.validate(request);

        assertTrue(result.isPresent(), "Ожидается ошибка, так как фамилия равна null");
        assertEquals("ERROR_CODE_6", result.get().getErrorCode());
        assertEquals("Field country is empty!", result.get().getDescription());
    }
}
