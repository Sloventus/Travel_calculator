package org.javaguru.travel.insurance.core.validations.person;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.api.dto.PersonDTO;
import org.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.javaguru.travel.insurance.core.repositories.MedicalRiskLimitLevelRepository;
import org.javaguru.travel.insurance.core.util.Placeholder;
import org.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class MedicalRiskLimitLevelEnableValidation extends TravelPersonFieldValidationImpl {


    private final ValidationErrorFactory validationErrorFactory;
    private final MedicalRiskLimitLevelRepository medicalRiskLimitLevelRepository;

    @Override
    public Optional<ValidationErrorDTO> validate(PersonDTO request) {
        boolean empty = request.getMedicalRiskLimitLevel() == null
                || request.getMedicalRiskLimitLevel().isEmpty();

        return !empty
                && isNotExists(request.getMedicalRiskLimitLevel())
                ? Optional.of(validationErrorFactory.buildError("ERROR_CODE_10"
                        , List.of(new Placeholder(request.getMedicalRiskLimitLevel()
                                , request.getMedicalRiskLimitLevel()))))
                : Optional.empty();
    }

    private boolean isNotExists(String medicalRiskLimitLevel) {
        return medicalRiskLimitLevelRepository
                .findByMedicalRiskLimitLevel(medicalRiskLimitLevel)
                .isEmpty();
    }
}
