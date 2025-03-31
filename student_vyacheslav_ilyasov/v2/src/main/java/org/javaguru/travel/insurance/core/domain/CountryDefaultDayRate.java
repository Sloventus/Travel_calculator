package org.javaguru.travel.insurance.core.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "country_default_day_rate")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CountryDefaultDayRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "country_ic")
    private String countryIc;

    @Column(name = "default_day_rate")
    private BigDecimal defaultDayRate;
}
