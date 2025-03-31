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
public class AgeCoefficientRepositoryTest {

    @Autowired
    private AgeCoefficientRepository ageCoefficientRepository;

    @Test
    public void testFindCoefficientByAge() {
        Optional<BigDecimal> result = ageCoefficientRepository.findCoefficientByAge(30L);

        assertTrue(result.isPresent());
        assertEquals(new BigDecimal("1.10"), result.get());
    }

    @Test
    public void testFindCoefficientByAge_NotFound() {
        Optional<BigDecimal> result = ageCoefficientRepository.findCoefficientByAge(170L);

        assertFalse(result.isPresent());
    }
}