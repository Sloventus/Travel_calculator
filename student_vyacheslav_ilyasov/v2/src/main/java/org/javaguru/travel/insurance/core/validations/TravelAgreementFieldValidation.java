package org.javaguru.travel.insurance.core.validations;

import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;

import java.util.List;
import java.util.Optional;

public interface TravelAgreementFieldValidation {

    Optional<ValidationErrorDTO> validate(AgreementDTO request);

    List<ValidationErrorDTO> valitadeList(AgreementDTO request);
}
