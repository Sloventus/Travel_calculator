package org.javaguru.travel.insurance.core.util;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Component
public class DateTimeUtil {
    public BigDecimal calculateDaysBetweenDates(LocalDate agreementDateFrom, LocalDate agreementDateTo) {
        return BigDecimal.valueOf(Math.abs(ChronoUnit.DAYS.between(agreementDateFrom, agreementDateTo)));
    }

    public Long getTimeByBirthday(LocalDate birthday) {
        return ChronoUnit.DAYS.between(birthday, LocalDate.now());
    }

    public Long getYearByBirthday(LocalDate birthday) {
        return ChronoUnit.YEARS.between(birthday, LocalDate.now());
    }

}
