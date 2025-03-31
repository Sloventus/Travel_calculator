package org.javaguru.travel.insurance.core.validations;

import lombok.AllArgsConstructor;
import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Component
class TravelAgreementValidatorImpl implements TravelAgreementValidator{

    private final ErrorsFinderAgreement agreementErrorsFinder;
    private final ErrorsFinderPerson personErrorsFinder;


    public List<ValidationErrorDTO> validate(AgreementDTO request) {
        return getTogether(agreementErrorsFinder.getSingleErrorsAgreement(request)
                , agreementErrorsFinder.getListErrorsAgreement(request)
                , personErrorsFinder.getErrorPerson(request));
    }

    private static List<ValidationErrorDTO> getTogether(
            List<ValidationErrorDTO> singleValidationErrors
            , List<ValidationErrorDTO> validationErrorsFromList
            , List<ValidationErrorDTO> getErrorPerson)
    {
        List<ValidationErrorDTO> result = new ArrayList<>();
        result.addAll(singleValidationErrors);
        result.addAll(validationErrorsFromList);
        result.addAll(getErrorPerson);
        return result;
    }
}
