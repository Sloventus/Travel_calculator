package org.javaguru.travel.insurance.core.underwriting.calculators.medical.integration;

import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.api.dto.AgreementDTOBuilder;
import org.javaguru.travel.insurance.core.api.dto.PersonDTO;
import org.javaguru.travel.insurance.core.api.dto.PersonDTOBuilder;
import org.javaguru.travel.insurance.core.underwriting.calculators.medical.AgeCoefficientCalculator;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Disabled
@ExtendWith(SpringExtension.class)
@SpringBootTest(properties = {"age.coeficient.enable=false"})
class AgeCoefficientCalculatorFalseTest {

    @Autowired
    AgeCoefficientCalculator ageCoefficientCalculator;

    @Test
    public void shouldReturnOne() {
        PersonDTO personDTO = PersonDTOBuilder.createPersonDTO()
                .withPersonFirstName("Vasiliy")
                .withPersonLastName("Pupiculiy")
                .withPersonBirthDate(LocalDate.parse("2024-03-03"))
                .withMedicalRiskLimitLevel("LEVEL_20000")
                .build();

        AgreementDTO agreementDTO = AgreementDTOBuilder.createAgreement()
                .withDateTo(LocalDate.parse("2125-03-03"))
                .withDateFrom(LocalDate.parse("2125-04-03"))
                .withCountry("Japan")
                .withSelectedRisk("TRAVEL_MEDICAL")
                .withPerson(personDTO)
                .build();

        assertTrue(BigDecimal.ONE.compareTo(ageCoefficientCalculator.getCoefficient(agreementDTO,personDTO)) == 0);
    }
}