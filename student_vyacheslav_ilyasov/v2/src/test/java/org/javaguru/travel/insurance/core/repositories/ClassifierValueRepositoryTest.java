package org.javaguru.travel.insurance.core.repositories;

import org.javaguru.travel.insurance.core.domain.ClassifierValue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@DataJpaTest
class ClassifierValueRepositoryTest {

    @Autowired private ClassifierValueRepository classifierValueRepository;

    @Test
    public void injectedRepositoryAreNotNull() {
        assertNotNull(classifierValueRepository);
    }


    @ParameterizedTest
    @ValueSource(strings = {"TRAVEL_CANCELLATION"
            , "TRAVEL_MEDICAL"
            , "TRAVEL_LOSS_BAGGAGE"
            , "TRAVEL_THIRD_PARTY_LIABILITY"
            , "TRAVEL_EVACUATION"
            , "TRAVEL_SPORT_ACTIVITIES"})
    public void parametrizedRiskTypeTest(String risktype) {
        assertionsByRisktype(risktype);
    }

    @Test
    public void shouldNotFind_RiskType_FAKE() {
        Optional<ClassifierValue> valueOpt = classifierValueRepository.findByClassifierTitleAndIc(
                "RISK_TYPE", "FAKE");
        assertTrue(valueOpt.isEmpty());
    }

    private void assertionsByRisktype(String riskType) {
        Optional<ClassifierValue> valueOpt = classifierValueRepository.findByClassifierTitleAndIc(
                "RISK_TYPE", riskType);
        assertTrue(valueOpt.isPresent());
        assertEquals(riskType, valueOpt.get().getIc());
        assertEquals("RISK_TYPE", valueOpt.get().getClassifier().getTitle());
    }
}