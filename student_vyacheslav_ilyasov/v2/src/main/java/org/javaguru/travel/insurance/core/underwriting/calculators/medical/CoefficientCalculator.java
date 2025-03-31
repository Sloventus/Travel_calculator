package org.javaguru.travel.insurance.core.underwriting.calculators.medical;

import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.api.dto.PersonDTO;

import java.math.BigDecimal;

interface CoefficientCalculator {
    BigDecimal getCoefficient(AgreementDTO agreement, PersonDTO person);
}
