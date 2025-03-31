package org.javaguru.travel.insurance.core.repositories;

import org.javaguru.travel.insurance.core.domain.CountryDefaultDayRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Optional;

public interface CountryDefaultDayRateRepository extends JpaRepository<CountryDefaultDayRate,Long> {

    @Query("SELECT c.defaultDayRate FROM CountryDefaultDayRate c WHERE c.countryIc = :country")
    Optional<BigDecimal> findDayRateByCountry(@Param("country") String country);
}
