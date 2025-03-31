package org.javaguru.travel.insurance.core.validations.agreement;

import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ValidatorSelectedRisksValidationEmptyTest {

    @Mock
    private ValidationErrorFactory validationErrorFactory;

    @InjectMocks
    private SelectedRisksEmptyValidation validatorSelectedRisksEmptyValidation;

    @Test
    public void testSelectedRisksEmpty() {
        AgreementDTO request = new AgreementDTO();
        request.setSelectedRisks(new ArrayList<>());

        when(validationErrorFactory.buildError("ERROR_CODE_4")).thenReturn(
                new ValidationErrorDTO(
                        "ERROR_CODE_4",
                        "Must not be empty!"));

        Optional<ValidationErrorDTO> result = validatorSelectedRisksEmptyValidation.validate(request);
        assertEquals("Must not be empty!",result.get().getDescription());
    }

    @Test
    public void testSelectedRisksNotEmpty() {
        AgreementDTO request = new AgreementDTO();
        List<String> selectedRisk = new ArrayList<>();
        selectedRisk.add("TRAVEL_MEDICAL");
        request.setSelectedRisks(selectedRisk);
        Optional<ValidationErrorDTO> result = validatorSelectedRisksEmptyValidation.validate(request);
        assertTrue(result.isEmpty());
    }
}
