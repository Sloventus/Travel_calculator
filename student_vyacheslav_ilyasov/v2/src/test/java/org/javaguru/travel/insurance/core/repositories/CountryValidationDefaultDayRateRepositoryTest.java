package org.javaguru.travel.insurance.core.repositories;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CountryValidationDefaultDayRateRepositoryTest {

    @Autowired
    private CountryDefaultDayRateRepository countryDefaultDayRateRepository;

    @Test
    public void testFindDayRateByCountry() {
        Optional<BigDecimal> result = countryDefaultDayRateRepository.findDayRateByCountry("JAPAN");

        assertTrue(result.isPresent());
        assertEquals(new BigDecimal("3.50"), result.get());
    }

    @Test
    public void testFindDayRateByCountry_NotFound() {
        Optional<BigDecimal> result = countryDefaultDayRateRepository.findDayRateByCountry("CA");

        assertFalse(result.isPresent());
    }
}