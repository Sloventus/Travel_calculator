package org.javaguru.travel.insurance.core.repositories;

import org.javaguru.travel.insurance.core.domain.AgeCoefficient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Optional;

public interface AgeCoefficientRepository
        extends JpaRepository<AgeCoefficient, Long>
{

    @Query("SELECT coef.coefficient FROM AgeCoefficient coef " +
            "WHERE coef.ageFrom <= :age AND coef.ageTo >= :age")
    Optional<BigDecimal> findCoefficientByAge(@Param("age") Long age);

}
