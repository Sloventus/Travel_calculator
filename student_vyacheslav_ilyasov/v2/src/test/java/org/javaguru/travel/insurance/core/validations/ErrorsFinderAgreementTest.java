package org.javaguru.travel.insurance.core.validations;

import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ErrorsFinderAgreementTest {


    @Mock
    private TravelAgreementFieldValidation travelAgreementFieldValidation1;

    @Mock
    private TravelAgreementFieldValidation travelAgreementFieldValidation2;


    @Test
    void getListErrorsAgreement() {
        AgreementDTO agreementDTO = new AgreementDTO();
        List<ValidationErrorDTO> errorDTOList = List.of(new ValidationErrorDTO(),new ValidationErrorDTO());
        when(travelAgreementFieldValidation1.valitadeList(agreementDTO)).thenReturn(errorDTOList);
        when(travelAgreementFieldValidation2.valitadeList(agreementDTO)).thenReturn(errorDTOList);
        ErrorsFinderAgreement errorsFinderAgreement = new ErrorsFinderAgreement(List.of(travelAgreementFieldValidation1,travelAgreementFieldValidation2));

        assertEquals(4, errorsFinderAgreement.getListErrorsAgreement(agreementDTO).size());
    }

    @Test
    void getSingleErrorsAgreement() {
        AgreementDTO agreementDTO = new AgreementDTO();
        var dtoOptional = Optional.of(new ValidationErrorDTO());
        when(travelAgreementFieldValidation1.validate(agreementDTO)).thenReturn(dtoOptional);
        when(travelAgreementFieldValidation2.validate(agreementDTO)).thenReturn(dtoOptional);
        ErrorsFinderAgreement errorsFinderAgreement = new ErrorsFinderAgreement(List.of(travelAgreementFieldValidation1,travelAgreementFieldValidation2));

        assertEquals(2, errorsFinderAgreement.getSingleErrorsAgreement(agreementDTO).size());
    }
}