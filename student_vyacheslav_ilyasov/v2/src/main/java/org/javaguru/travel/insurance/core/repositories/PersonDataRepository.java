package org.javaguru.travel.insurance.core.repositories;

import org.javaguru.travel.insurance.core.domain.PersonData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PersonDataRepository extends JpaRepository<PersonData,Long> {

    PersonData save(PersonData personData);

    @Query("SELECT p FROM PersonData p WHERE " +
            "p.firstName = :#{#person.firstName} AND " +
            "p.lastName = :#{#person.lastName} AND " +
            "p.birthDate = :#{#person.birthDate} AND " +
            "p.personCode = :#{#person.personCode}")
    Optional<PersonData> findByAllFields(@Param("person") PersonData person);
}
