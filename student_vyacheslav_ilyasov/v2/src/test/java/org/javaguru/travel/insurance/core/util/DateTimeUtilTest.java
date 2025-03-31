package org.javaguru.travel.insurance.core.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;


public class DateTimeUtilTest {


    private final LocalDate date1 = LocalDate.of(2010, 11, 11);
    private final LocalDate date2 = LocalDate.of(2011, 11, 11);

    private final DateTimeUtil dateTimeUtil = new DateTimeUtil();

    @Test
    public void calculateDaysBetweenDatesTest() {
        BigDecimal daysBetween = dateTimeUtil.calculateDaysBetweenDates(date2, date1);
        Assertions.assertEquals(365, daysBetween.longValue());
    }
}
