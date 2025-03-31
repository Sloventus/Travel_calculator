package org.javaguru.travel.insurance.core.validations.agreement;

import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.javaguru.travel.insurance.core.domain.ClassifierValue;
import org.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import org.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ValidatorCountryValidationTest {

    @Mock
    private ClassifierValueRepository classifierValueRepository;

    @Mock
    private ValidationErrorFactory validationErrorFactory;

    @InjectMocks
    private CountryValidation validatorCountryValidation;

    @Test
    void validate_WhenCountryDoesNotExist_ShouldReturnValidationError() {
        AgreementDTO request = new AgreementDTO();
        request.setCountry("JAPAN");

        when(classifierValueRepository.findByClassifierTitleAndIc("COUNTRY", "JAPAN"))
                .thenReturn(Optional.empty());


        ValidationErrorDTO errorCode7 = new ValidationErrorDTO(
                "ERROR_CODE_7"
                , "Country {JAPAN} not supported!");

        when(validationErrorFactory.buildError(anyString(), anyList())).thenReturn(errorCode7);

        Optional<ValidationErrorDTO> result = validatorCountryValidation.validate(request);

        assertTrue(result.isPresent());
        assertEquals("ERROR_CODE_7", result.get().getErrorCode());
        assertEquals("Country {JAPAN} not supported!", result.get().getDescription());

        verify(classifierValueRepository, times(1))
                .findByClassifierTitleAndIc("COUNTRY", "JAPAN");

        verify(validationErrorFactory, times(1)).buildError(anyString(), anyList());
    }

    @Test
    void validate_WhenCountryExists_ShouldReturnEmptyOptional() {
        AgreementDTO request = new AgreementDTO();
        request.setCountry("USA");

        when(classifierValueRepository.findByClassifierTitleAndIc("COUNTRY", "USA"))
                .thenReturn(Optional.of(new ClassifierValue()));

        Optional<ValidationErrorDTO> result = validatorCountryValidation.validate(request);

        assertTrue(result.isEmpty());

        verify(classifierValueRepository, times(1)).findByClassifierTitleAndIc("COUNTRY", "USA");
        verify(validationErrorFactory, never()).buildError(anyString(), anyList());
    }
}