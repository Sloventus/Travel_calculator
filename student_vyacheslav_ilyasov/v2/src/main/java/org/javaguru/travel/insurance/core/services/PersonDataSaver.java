package org.javaguru.travel.insurance.core.services;

import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.api.dto.PersonDTO;
import org.javaguru.travel.insurance.core.domain.PersonData;
import org.javaguru.travel.insurance.core.repositories.PersonDataRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PersonDataSaver {

    private final PersonDataRepository personDataRepository;

    public void save(AgreementDTO agreementDTO) {

        agreementDTO.getPersons().stream()
                .filter(p -> personDataRepository.findByAllFields(getPersonData(p)).isEmpty())
                .forEach(personDTO -> {
                    PersonData personData = getPersonData(personDTO);
                    personDataRepository.save(personData);
                });
    }

    private PersonData getPersonData(PersonDTO personDTO) {
        PersonData personData = new PersonData();
        personData.setFirstName(personDTO.getPersonFirstName());
        personData.setLastName(personDTO.getPersonLastName());
        personData.setPersonCode(personDTO.getPersonCode());
        personData.setBirthDate(personDTO.getPersonBirthDate());
        return personData;
    }
}
