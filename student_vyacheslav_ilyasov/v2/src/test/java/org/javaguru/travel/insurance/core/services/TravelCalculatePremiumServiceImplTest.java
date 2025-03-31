package org.javaguru.travel.insurance.core.services;

import org.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreCommand;
import org.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreResult;
import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.javaguru.travel.insurance.core.validations.TravelAgreementValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TravelCalculatePremiumServiceImplTest {

    @Mock
    private TravelAgreementValidator agreementValidator;

    @Mock
    private ResponseFactory responseFactory;

    @Mock
    private PersonDataSaver personDataSaver;

    @InjectMocks
    private TravelCalculatePremiumServiceImpl travelCalculatePremiumService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCalculatePremiumWithValidAgreement() {
        TravelCalculatePremiumCoreCommand command = new TravelCalculatePremiumCoreCommand();
        AgreementDTO agreement = new AgreementDTO();
        command.setAgreement(agreement);

        when(agreementValidator.validate(agreement)).thenReturn(Collections.emptyList());
        TravelCalculatePremiumCoreResult expectedResult = new TravelCalculatePremiumCoreResult(agreement);
        when(responseFactory.buildResponse(agreement)).thenReturn(expectedResult);

        TravelCalculatePremiumCoreResult result = travelCalculatePremiumService.calculatePremium(command);

        assertEquals(expectedResult, result);
        verify(agreementValidator).validate(agreement);
        verify(responseFactory).buildResponse(agreement);
        verify(responseFactory, never()).buildResponse(anyList());
    }

    @Test
    void testCalculatePremiumWithInvalidAgreement() {
        TravelCalculatePremiumCoreCommand command = new TravelCalculatePremiumCoreCommand();
        AgreementDTO agreement = new AgreementDTO();
        command.setAgreement(agreement);

        List<ValidationErrorDTO> errors = Collections.singletonList(new ValidationErrorDTO());
        when(agreementValidator.validate(agreement)).thenReturn(errors);
        TravelCalculatePremiumCoreResult expectedResult = new TravelCalculatePremiumCoreResult(errors);
        when(responseFactory.buildResponse(errors)).thenReturn(expectedResult);

        TravelCalculatePremiumCoreResult result = travelCalculatePremiumService.calculatePremium(command);

        assertEquals(expectedResult, result);
        verify(agreementValidator).validate(agreement);
        verify(responseFactory).buildResponse(errors);
        verify(responseFactory, never()).buildResponse(any(AgreementDTO.class));
    }
}