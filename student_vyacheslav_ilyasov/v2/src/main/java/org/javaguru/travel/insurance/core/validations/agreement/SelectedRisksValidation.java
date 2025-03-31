package org.javaguru.travel.insurance.core.validations.agreement;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import org.javaguru.travel.insurance.core.util.Placeholder;
import org.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class SelectedRisksValidation extends TravelAgreementFieldValidationImpl {

    private final ValidationErrorFactory validationErrorFactory;
    private final ClassifierValueRepository classifierValueRepository;


    @Override
    public List<ValidationErrorDTO> valitadeList(AgreementDTO request) {

        return request.getSelectedRisks() == null || request.getSelectedRisks().isEmpty()
                ? List.of(validationErrorFactory.buildError("ERROR_CODE_4"))
                : request.getSelectedRisks().stream()
                .filter(this::isNotExist)
                .map(s -> new Placeholder(s, s))
                .map(p ->
                    validationErrorFactory.buildError("ERROR_CODE_5", List.of(p)))
                .toList();
    }

    private boolean isNotExist(String ic) {
        return classifierValueRepository
                .findByClassifierTitleAndIc("RISK_TYPE", ic)
                .isEmpty();
    }

}
