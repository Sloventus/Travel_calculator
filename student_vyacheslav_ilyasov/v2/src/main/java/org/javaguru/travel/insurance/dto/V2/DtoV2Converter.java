package org.javaguru.travel.insurance.dto.V2;

import org.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreCommand;
import org.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreResult;
import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.api.dto.PersonDTO;
import org.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.javaguru.travel.insurance.dto.RiskPremium;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class DtoV2Converter {

    public TravelCalculatePremiumCoreCommand buildCommand(TravelCalculatePremiumRequestV2 request) {
        AgreementDTO agreementDTO = new AgreementDTO();

        agreementDTO.setAgreementDateFrom(request.getAgreementDateFrom());
        agreementDTO.setAgreementDateTo(request.getAgreementDateTo());
        agreementDTO.setCountry(request.getCountry());
        agreementDTO.setSelectedRisks(request.getSelectedRisks());
        agreementDTO.setPersons(buildPersonDTOs(request));

        return new TravelCalculatePremiumCoreCommand(agreementDTO);
    }

    public TravelCalculatePremiumResponseV2 buildResponseV2(TravelCalculatePremiumCoreResult result) {
        return result.hasErrors() ?
                buildResponseV2(result.getErrors()) :
                buildResponseV2(result.getAgreement());
    }


    private List<PersonDTO> buildPersonDTOs(TravelCalculatePremiumRequestV2 request) {
        return request.getPersons() == null
                ? List.of(new PersonDTO())
                : request.getPersons().stream()
                .map(p -> {
                    PersonDTO personDTO = new PersonDTO();
                    personDTO.setPersonLastName(p.getPersonLastName());
                    personDTO.setPersonFirstName(p.getPersonFirstName());
                    personDTO.setPersonBirthDate(p.getBirthDate());
                    personDTO.setMedicalRiskLimitLevel(p.getMedicalRiskLimitLevel());
                    personDTO.setPersonCode(p.getPersonCode());
                    return personDTO;
                }).toList();
    }



    private TravelCalculatePremiumResponseV2 buildResponseV2(List<ValidationErrorDTO> errors) {
        return new TravelCalculatePremiumResponseV2(errors.stream()
                .map(error -> new ValidationError(error.getErrorCode(), error.getDescription() ) )
                .toList());
    }

    private TravelCalculatePremiumResponseV2 buildResponseV2(AgreementDTO agreementDTO) {
        TravelCalculatePremiumResponseV2 travelCalculatePremiumResponseV2 = new TravelCalculatePremiumResponseV2();

        travelCalculatePremiumResponseV2.setPersons(buildPersonResponse(agreementDTO));

        travelCalculatePremiumResponseV2.setAgreementDateFrom(agreementDTO.getAgreementDateFrom());
        travelCalculatePremiumResponseV2.setAgreementDateTo(agreementDTO.getAgreementDateTo());
        travelCalculatePremiumResponseV2.setAgreementPremium(agreementDTO.getAgreementPremium());
        travelCalculatePremiumResponseV2.setCountry(agreementDTO.getCountry());

        return travelCalculatePremiumResponseV2;
    }

    private List<PersonResponse> buildPersonResponse(AgreementDTO agreementDTO) {
        return agreementDTO.getPersons().stream()
                .map(p -> { PersonResponse personResponse = new PersonResponse();
                    personResponse.setPersonFirstName(p.getPersonFirstName());
                    personResponse.setPersonLastName(p.getPersonLastName());
                    personResponse.setBirthDate(p.getPersonBirthDate());
                    personResponse.setRisks(buildRiskPremiums(p));
                    personResponse.setMedicalRiskLimitLevel(p.getMedicalRiskLimitLevel());
                    personResponse.setPersonPremium(personResponse.getRisks().stream()
                            .map(r -> r.getPremium())
                            .reduce(BigDecimal.ZERO, BigDecimal::add));
                    personResponse.setPersonCode(p.getPersonCode());
                    return personResponse;
                })
                .toList();
    }

    private List<RiskPremium> buildRiskPremiums(PersonDTO personDTO) {
        return personDTO.getRisks().stream()
                .map(r -> new RiskPremium(r.getRiskIc(),r.getPremium()))
                .toList();
    }
}
