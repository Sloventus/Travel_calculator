package org.javaguru.travel.insurance.core.validations.person;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.api.dto.PersonDTO;
import org.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.javaguru.travel.insurance.core.util.DateTimeUtil;
import org.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class BirthDatesValidation extends TravelPersonFieldValidationImpl {

    private final ValidationErrorFactory validationErrorFactory;
    private final DateTimeUtil dateTimeUtil;

    @Override
    public Optional<ValidationErrorDTO> validate(PersonDTO request) {
        return
                (request.getPersonBirthDate() == null ||
                        !((dateTimeUtil.getTimeByBirthday(request.getPersonBirthDate())) >0))
                ? Optional.of(validationErrorFactory.buildError("ERROR_CODE_8"))
                : Optional.empty();
    }
}
