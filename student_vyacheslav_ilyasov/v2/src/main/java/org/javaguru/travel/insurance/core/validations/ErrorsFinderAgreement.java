package org.javaguru.travel.insurance.core.validations;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class ErrorsFinderAgreement {

    private final List<TravelAgreementFieldValidation> agreementValidations;

    public List<ValidationErrorDTO> getListErrorsAgreement(AgreementDTO request) {
        return agreementValidations.stream()
                .map(e -> e.valitadeList(request))
                .flatMap(e -> e.stream())
                .toList();
    }

    public List<ValidationErrorDTO> getSingleErrorsAgreement(AgreementDTO request) {
        return agreementValidations.stream()
                .map(e -> e.validate(request))
                .filter(e -> e.isPresent())
                .map(Optional::get)
                .toList();
    }
}
