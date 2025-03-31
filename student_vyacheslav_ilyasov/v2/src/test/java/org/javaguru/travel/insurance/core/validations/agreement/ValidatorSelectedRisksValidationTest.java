package org.javaguru.travel.insurance.core.validations.agreement;

import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.javaguru.travel.insurance.core.domain.ClassifierValue;
import org.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import org.javaguru.travel.insurance.core.util.Placeholder;
import org.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ValidatorSelectedRisksValidationTest {

    @Mock
    private ClassifierValueRepository classifierValueRepository;

    @Mock
    private ValidationErrorFactory validationErrorFactory;

    @InjectMocks
    private SelectedRisksValidation validatorSelectedRisksValidation;

    @Test
    void testValidateList_NoErrors() {
        AgreementDTO request = new AgreementDTO();
        request.setSelectedRisks(List.of("RISK_1", "RISK_2"));

        when(classifierValueRepository.findByClassifierTitleAndIc("RISK_TYPE", "RISK_1"))
                .thenReturn(Optional.of(new ClassifierValue()));
        when(classifierValueRepository.findByClassifierTitleAndIc("RISK_TYPE", "RISK_2"))
                .thenReturn(Optional.of(new ClassifierValue()));

        List<ValidationErrorDTO> errors = validatorSelectedRisksValidation.valitadeList(request);

        assertTrue(errors.isEmpty());
    }

    @Test
    void testValidateList_WithErrors() {
        AgreementDTO request = new AgreementDTO();
        request.setSelectedRisks(List.of("INVALID_RISK_1", "INVALID_RISK_2"));

        when(classifierValueRepository.findByClassifierTitleAndIc("RISK_TYPE", "INVALID_RISK_1"))
                .thenReturn(Optional.empty());
        when(classifierValueRepository.findByClassifierTitleAndIc("RISK_TYPE", "INVALID_RISK_2"))
                .thenReturn(Optional.empty());

        ValidationErrorDTO error1 = new ValidationErrorDTO("ERROR_CODE_5"
                , List.of(new Placeholder("INVALID_RISK_1", "INVALID_RISK_1")).toString());

        ValidationErrorDTO error2 = new ValidationErrorDTO("ERROR_CODE_5"
                , List.of(new Placeholder("INVALID_RISK_2", "INVALID_RISK_2")).toString());

        when(validationErrorFactory.buildError(eq("ERROR_CODE_5"), anyList()))
                .thenReturn(error1, error2);

        List<ValidationErrorDTO> errors = validatorSelectedRisksValidation.valitadeList(request);

        assertEquals(2, errors.size());
        assertEquals("ERROR_CODE_5", errors.get(0).getErrorCode());
        assertEquals("ERROR_CODE_5", errors.get(1).getErrorCode());
    }
}