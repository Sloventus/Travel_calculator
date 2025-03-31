package org.javaguru.travel.insurance.core.validations;

import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class TravelAgreementValidatorImplTest {

    private TravelAgreementValidatorImpl validator;

    @Mock
    private ErrorsFinderAgreement agreementErrorsFinder;

    @Mock
    private ErrorsFinderPerson personErrorsFinder;

    @Mock
    private AgreementDTO request;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        validator = new TravelAgreementValidatorImpl(agreementErrorsFinder, personErrorsFinder);
    }

    @Test
    void testValidate_ReturnsCorrectListSize() {
        ValidationErrorDTO error1 = new ValidationErrorDTO();
        ValidationErrorDTO error2 = new ValidationErrorDTO();
        ValidationErrorDTO error3 = new ValidationErrorDTO();

        when(agreementErrorsFinder.getSingleErrorsAgreement(request)).thenReturn(List.of(error1));
        when(agreementErrorsFinder.getListErrorsAgreement(request)).thenReturn(List.of(error2));
        when(personErrorsFinder.getErrorPerson(request)).thenReturn(List.of(error3));

        List<ValidationErrorDTO> errors = validator.validate(request);

        assertEquals(3, errors.size()); // Проверка размера списка
    }

    @Test
    void testValidate_ReturnsEmptyListWhenNoErrors() {
        when(agreementErrorsFinder.getSingleErrorsAgreement(request)).thenReturn(List.of());
        when(agreementErrorsFinder.getListErrorsAgreement(request)).thenReturn(List.of());
        when(personErrorsFinder.getErrorPerson(request)).thenReturn(List.of());

        List<ValidationErrorDTO> errors = validator.validate(request);

        assertEquals(0, errors.size()); // Проверка, что список пуст
    }
}