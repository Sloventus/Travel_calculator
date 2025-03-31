package org.javaguru.travel.insurance.core.underwriting;

import org.javaguru.travel.insurance.core.api.dto.RiskDTO;

import java.math.BigDecimal;
import java.util.List;

public record TravelPremiumCalculationResult(BigDecimal agreementPremium, List<RiskDTO> risks) {}
