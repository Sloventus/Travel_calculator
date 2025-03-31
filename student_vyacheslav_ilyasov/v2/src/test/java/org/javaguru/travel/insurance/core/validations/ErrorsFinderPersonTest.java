package org.javaguru.travel.insurance.core.validations;

import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.api.dto.PersonDTO;
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
class ErrorsFinderPersonTest {

    @Mock
    private TravelPersonFieldValidation personValidations1;

    @Mock
    private TravelPersonFieldValidation personValidations2;

    @Test
    void getErrorPerson() {
        PersonDTO personDTO = new PersonDTO();
        AgreementDTO agreementDTO = new AgreementDTO();
        agreementDTO.setPersons(List.of(personDTO));
        when(personValidations1.validate(personDTO)).thenReturn(Optional.of(new ValidationErrorDTO()));
        when(personValidations2.validate(personDTO)).thenReturn(Optional.of(new ValidationErrorDTO()));
        ErrorsFinderPerson errorsFinderPerson = new ErrorsFinderPerson(List.of(personValidations1, personValidations2));

        assertEquals(2, errorsFinderPerson.getErrorPerson(agreementDTO).size());
    }
}