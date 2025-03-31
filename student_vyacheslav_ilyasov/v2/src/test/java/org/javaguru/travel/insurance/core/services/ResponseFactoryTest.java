package org.javaguru.travel.insurance.core.services;

import org.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreResult;
import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ResponseFactoryTest {

    @Mock
    private CalculatorRiskPremiumsForAllPersons calculatorRiskPremiumsForAllPersons;

    @Mock
    private CalculatorTotalAgreementPremium calculatorTotalAgreementPremium;

    @InjectMocks
    private ResponseFactory responseFactory;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testBuildResponseWithErrors() {
        List<ValidationErrorDTO> errors = Collections.singletonList(new ValidationErrorDTO());

        TravelCalculatePremiumCoreResult result = responseFactory.buildResponse(errors);

        assertEquals(errors, result.getErrors());
        verifyNoInteractions(calculatorRiskPremiumsForAllPersons, calculatorTotalAgreementPremium);
    }

    @Test
    void testBuildResponseWithAgreement() {
        AgreementDTO agreement = new AgreementDTO();
        when(calculatorTotalAgreementPremium.calculate(agreement)).thenReturn(BigDecimal.ONE);

        TravelCalculatePremiumCoreResult result = responseFactory.buildResponse(agreement);

        assertEquals(agreement, result.getAgreement());
        verify(calculatorRiskPremiumsForAllPersons).calculate(agreement);
        verify(calculatorTotalAgreementPremium).calculate(agreement);
    }
}