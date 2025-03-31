package org.javaguru.travel.insurance.core.repositories;

import org.javaguru.travel.insurance.core.domain.MedicalRiskLimitLevel;
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
public class MedicalRiskLimitLevelRepositoryTest {

    @Autowired
    private MedicalRiskLimitLevelRepository medicalRiskLimitLevelRepository;

    @Test
    public void testFindByMedicalRiskLimitLevel() {
        Optional<MedicalRiskLimitLevel> result = medicalRiskLimitLevelRepository.findByMedicalRiskLimitLevel("LEVEL_10000");
        BigDecimal value = new BigDecimal("1.00");

        assertTrue(result.isPresent());
        assertEquals(value, result.get().getCoefficient());
    }

    @Test
    public void testFindByMedicalRiskLimitLevel_NotFound() {
        Optional<MedicalRiskLimitLevel> result = medicalRiskLimitLevelRepository.findByMedicalRiskLimitLevel("LEVEL_2");

        assertFalse(result.isPresent());
    }
}