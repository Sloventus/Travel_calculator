package org.javaguru.travel.insurance.core.api.dto;

import java.time.LocalDate;
import java.util.List;

public class PersonDTOBuilder {

    private String personFirstName;
    private String personLastName;
    private LocalDate personBirthDate;
    private List<RiskDTO> risks;
    private String medicalRiskLimitLevel;
    private String personCode;

    public static PersonDTOBuilder createPersonDTO() {
        return new PersonDTOBuilder();
    }

    public PersonDTO build() {
        return new PersonDTO(personFirstName
        ,personLastName
        ,personBirthDate
        ,risks
        ,medicalRiskLimitLevel
        ,personCode);
    }

    public PersonDTOBuilder withPersonFirstName(String personFirstName) {
        this.personFirstName = personFirstName;
        return this;
    }

    public PersonDTOBuilder withPersonLastName(String personLastName) {
        this.personLastName = personLastName;
        return this;
    }

    public PersonDTOBuilder withPersonBirthDate(LocalDate personBirthDate) {
        this.personBirthDate = personBirthDate;
        return this;
    }

    public PersonDTOBuilder withRisks(List<RiskDTO> risks) {
        this.risks = risks;
        return this;
    }

    public PersonDTOBuilder withMedicalRiskLimitLevel(String medicalRiskLimitLevel) {
        this.medicalRiskLimitLevel = medicalRiskLimitLevel;
        return this;
    }

    public PersonDTOBuilder withPersonCode(String personCode) {
        this.personCode = personCode;
        return  this;
    }
}
