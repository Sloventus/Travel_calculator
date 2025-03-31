package org.javaguru.travel.insurance.core.validations.person;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.api.dto.PersonDTO;
import org.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class MedicalRiskLimitLevelEnableEmptyValidation extends TravelPersonFieldValidationImpl {


    private final ValidationErrorFactory validationErrorFactory;

    @Override
    public Optional<ValidationErrorDTO> validate(PersonDTO request) {
        boolean empty = request.getMedicalRiskLimitLevel() == null
                || request.getMedicalRiskLimitLevel().isEmpty();

        return empty
                ? Optional.of(validationErrorFactory.buildError("ERROR_CODE_9"))
                : Optional.empty();
    }
}
