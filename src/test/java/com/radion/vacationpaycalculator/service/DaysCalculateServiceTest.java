package com.radion.vacationpaycalculator.service;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.format.DateTimeParseException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class DaysCalculateServiceTest {
    private final DaysCalculateService daysCalculateService = new DaysCalculateService();

    private static Stream<Arguments> countWorkDaysTestCases() {
        return Stream.of(
                Arguments.of(DaysCalculateService.YEAR + "-01-03", DaysCalculateService.YEAR + "-01-05", 0),
                Arguments.of(DaysCalculateService.YEAR + "-01-01", DaysCalculateService.YEAR + "-01-01", 0),
                Arguments.of(DaysCalculateService.YEAR + "-01-06", DaysCalculateService.YEAR + "-01-08", 0),
                Arguments.of(DaysCalculateService.YEAR + "-01-01", DaysCalculateService.YEAR + "-01-31", 17),
                Arguments.of(DaysCalculateService.YEAR + "-01-01", DaysCalculateService.YEAR + "-02-28", 36),
                Arguments.of(DaysCalculateService.YEAR + "-01-01", DaysCalculateService.YEAR + "-12-31", 249),
                Arguments.of(DaysCalculateService.YEAR + "-05-01", DaysCalculateService.YEAR + "-05-09", 5),
                Arguments.of(DaysCalculateService.YEAR + "-06-13", DaysCalculateService.YEAR + "-06-17", 3),
                Arguments.of(DaysCalculateService.YEAR + "-11-03", DaysCalculateService.YEAR + "-11-07", 3),
                Arguments.of(DaysCalculateService.YEAR + "-12-26", DaysCalculateService.YEAR + "-12-31", 3)
        );
    }

    private static Stream<Arguments> exceptionParseTestCases() {
        return Stream.of(
                Arguments.of(DaysCalculateService.YEAR + "-22-03", DaysCalculateService.YEAR + "-01-05", DateTimeParseException.class),
                Arguments.of(DaysCalculateService.YEAR + "-01-100", DaysCalculateService.YEAR + "-01-", DateTimeParseException.class),
                Arguments.of("01-06-" + DaysCalculateService.YEAR, DaysCalculateService.YEAR + "-101-08", DateTimeParseException.class),
                Arguments.of(DaysCalculateService.YEAR + "-00-00", DaysCalculateService.YEAR + "-000-00", DateTimeParseException.class)
        );
    }

    @ParameterizedTest
    @MethodSource("countWorkDaysTestCases")
    void getCountWorkDays_ValidInput_ReturnsExpectedResult(String startDate, String endDate, int expectedResult) {
        assertEquals(expectedResult, daysCalculateService.getCountWorkDays(startDate, endDate));
    }

    @ParameterizedTest
    @MethodSource("exceptionParseTestCases")
    void getCountWorkDays_InvalidInput_ThrowsDateTimeParseException(String startDate, String endDate, Class<? extends DateTimeParseException> expectedException) {
        assertThrows(expectedException, () -> daysCalculateService.getCountWorkDays(startDate, endDate));
    }
}