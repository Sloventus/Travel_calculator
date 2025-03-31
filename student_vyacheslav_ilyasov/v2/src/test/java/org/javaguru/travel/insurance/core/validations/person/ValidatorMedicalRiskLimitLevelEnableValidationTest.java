package org.javaguru.travel.insurance.core.validations.person;

import org.javaguru.travel.insurance.core.api.dto.PersonDTO;
import org.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.javaguru.travel.insurance.core.domain.MedicalRiskLimitLevel;
import org.javaguru.travel.insurance.core.repositories.MedicalRiskLimitLevelRepository;
import org.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidatorMedicalRiskLimitLevelEnableValidationTest {

    @Mock
    private MedicalRiskLimitLevelRepository medicalRiskLimitLevelRepository;

    @Mock
    private ValidationErrorFactory validationErrorFactory;

    @InjectMocks
    private MedicalRiskLimitLevelEnableValidation validator;


    @Test
    void testValidate_MedicalRiskLimitLevelEnabled_EmptyLevel() {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setMedicalRiskLimitLevel(null);

        Optional<ValidationErrorDTO> error = validator.validate(personDTO);

        assertTrue(error.isEmpty());
    }

    @Test
    void testValidate_MedicalRiskLimitLevelEnabled_LevelExists() {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setMedicalRiskLimitLevel("LEVEL_1");

        when(medicalRiskLimitLevelRepository.findByMedicalRiskLimitLevel("LEVEL_1"))
                .thenReturn(Optional.of(new MedicalRiskLimitLevel()));

        Optional<ValidationErrorDTO> error = validator.validate(personDTO);

        assertTrue(error.isEmpty());
    }

    @Test
    void testValidate_MedicalRiskLimitLevelEnabled_LevelNotExists() {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setMedicalRiskLimitLevel("INVALID_LEVEL");

        when(medicalRiskLimitLevelRepository.findByMedicalRiskLimitLevel("INVALID_LEVEL"))
                .thenReturn(Optional.empty());
        when(validationErrorFactory.buildError(anyString(), anyList()))
                .thenReturn(new ValidationErrorDTO("ERROR_CODE_10"
                        , "MedicalRiskLimitLevel INVALID_LEVEL not supported!"));

        Optional<ValidationErrorDTO> error = validator.validate(personDTO);

        assertTrue(error.isPresent());
        assertEquals("ERROR_CODE_10", error.get().getErrorCode());
    }
}