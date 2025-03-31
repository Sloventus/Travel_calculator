package org.javaguru.travel.insurance.dto.v1;

import org.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreCommand;
import org.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreResult;
import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.api.dto.PersonDTO;
import org.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.javaguru.travel.insurance.dto.RiskPremium;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DtoV1Converter {

    public TravelCalculatePremiumResponseV1 buildResponseV1(TravelCalculatePremiumCoreResult result) {
        return result.hasErrors() ?
                buildResponseV1(result.getErrors()) :
                buildResponseV1(result.getAgreement());
    }

    public TravelCalculatePremiumCoreCommand buildCommand(TravelCalculatePremiumRequestV1 request) {
        return new TravelCalculatePremiumCoreCommand(buildAgreementDTO(request));
    }


    private AgreementDTO buildAgreementDTO(TravelCalculatePremiumRequestV1 request) {
        AgreementDTO agreementDTO = new AgreementDTO();
        agreementDTO.setAgreementDateFrom(request.getAgreementDateFrom());
        agreementDTO.setAgreementDateTo(request.getAgreementDateTo());
        agreementDTO.setCountry(request.getCountry());
        agreementDTO.setSelectedRisks(request.getSelectedRisks());
        agreementDTO.setPersons(List.of(buildPersonDTO(request)));
        return agreementDTO;
    }

    private PersonDTO buildPersonDTO(TravelCalculatePremiumRequestV1 request) {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setPersonBirthDate(request.getBirthDate());
        personDTO.setPersonFirstName(request.getPersonFirstName());
        personDTO.setPersonLastName(request.getPersonLastName());
        personDTO.setMedicalRiskLimitLevel(request.getMedicalRiskLimitLevel());
        personDTO.setPersonCode(request.getPersonCode());
        return personDTO;
    }

    private TravelCalculatePremiumResponseV1 buildResponseV1(List<ValidationErrorDTO> errors) {
        return new TravelCalculatePremiumResponseV1(errors.stream()
                .map(error -> new ValidationError(error.getErrorCode(), error.getDescription() ) )
                .toList());
    }

    private TravelCalculatePremiumResponseV1 buildResponseV1(AgreementDTO agreementDTO) {
        TravelCalculatePremiumResponseV1 travelCalculatePremiumResponseV2 = new TravelCalculatePremiumResponseV1();
        PersonDTO personDTO = agreementDTO.getPersons().getFirst();

        travelCalculatePremiumResponseV2.setPersonFirstName(personDTO.getPersonFirstName());
        travelCalculatePremiumResponseV2.setPersonLastName(personDTO.getPersonLastName());
        travelCalculatePremiumResponseV2.setAgreementDateFrom(agreementDTO.getAgreementDateFrom());
        travelCalculatePremiumResponseV2.setAgreementDateTo(agreementDTO.getAgreementDateTo());
        travelCalculatePremiumResponseV2.setAgreementPremium(agreementDTO.getAgreementPremium());

        travelCalculatePremiumResponseV2.setRisks(personDTO.getRisks().stream()
                .map(r -> new RiskPremium(r.getRiskIc(),r.getPremium()) )
                .toList());

        travelCalculatePremiumResponseV2.setCountry(agreementDTO.getCountry());
        travelCalculatePremiumResponseV2.setBirthDate(personDTO.getPersonBirthDate());
        travelCalculatePremiumResponseV2.setMedicalRiskLimitLevel(personDTO.getMedicalRiskLimitLevel());
        travelCalculatePremiumResponseV2.setPersonCode(personDTO.getPersonCode());

        return travelCalculatePremiumResponseV2;
    }
}
