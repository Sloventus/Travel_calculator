package org.javaguru.travel.insurance.core.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "medical_risk_limit_level")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedicalRiskLimitLevel {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "MEDICAL_RISK_LIMIT_LEVEL_IC")
    private String medicalRiskLimitLevel;

    @Column(name = "COEFFICIENT")
    private BigDecimal coefficient;
}
