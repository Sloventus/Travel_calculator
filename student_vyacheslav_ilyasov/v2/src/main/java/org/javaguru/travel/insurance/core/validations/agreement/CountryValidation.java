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
import java.util.Optional;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class CountryValidation extends TravelAgreementFieldValidationImpl {

    private final ValidationErrorFactory validationErrorFactory;
    private final ClassifierValueRepository classifierValueRepository;

    @Override
    public Optional<ValidationErrorDTO> validate(AgreementDTO request) {
        return !(request.getCountry() == null) &&
                !request.getCountry().isEmpty() &&
                isNotExist(request.getCountry())
                ?
                Optional.of(validationErrorFactory
                        .buildError("ERROR_CODE_7"
                                , List.of(new Placeholder(request.getCountry(), request.getCountry()))
                        )
                )
                :
                Optional.empty();
    }

    private boolean isNotExist(String ic) {
        return classifierValueRepository
                .findByClassifierTitleAndIc("COUNTRY", ic.toUpperCase())
                .isEmpty();
    }
}
