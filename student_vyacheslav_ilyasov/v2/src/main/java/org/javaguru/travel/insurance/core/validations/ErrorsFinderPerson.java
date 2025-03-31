package org.javaguru.travel.insurance.core.validations;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class ErrorsFinderPerson {

    private final List<TravelPersonFieldValidation> personValidations;


    public List<ValidationErrorDTO> getErrorPerson(AgreementDTO request) {
        return request.getPersons()
                .stream()
                .map(person -> personValidations.stream()
                        .map(validation -> validation.validate(person))
                        .filter(o -> o.isPresent())
                        .map(validationErrorDTO -> validationErrorDTO.get())
                        .toList())
                .flatMap(e -> e.stream())
                .toList();

    }
}
