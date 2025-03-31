package org.javaguru.travel.insurance.core.validations.agreement;

import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.javaguru.travel.insurance.core.validations.TravelAgreementFieldValidation;

import java.util.List;
import java.util.Optional;

abstract class TravelAgreementFieldValidationImpl implements TravelAgreementFieldValidation {

    @Override
    public Optional<ValidationErrorDTO> validate(AgreementDTO request) {
        return Optional.empty();
    }

    @Override
    public List<ValidationErrorDTO> valitadeList(AgreementDTO request) {
        return List.of();
    }
}
