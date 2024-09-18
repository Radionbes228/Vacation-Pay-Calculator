package com.radion.vacationpaycalculator.service;


import com.radion.vacationpaycalculator.model.VacationSalaryResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class SalaryCalculatorServiceTest {

    private SalaryCalculatorService salaryCalculatorService;

    @BeforeEach
    void setUp() {
        MessageSource messageSource = new ResourceBundleMessageSource();
        DaysCalculateService daysCalculateService = new DaysCalculateService();
        salaryCalculatorService = new SalaryCalculatorService(messageSource, daysCalculateService);
    }


    private static Stream<Arguments> provideDataForCalculateSalaryPay() {
        return Stream.of(
                Arguments.of(1000f, DaysCalculateService.YEAR + "-01-01", DaysCalculateService.YEAR + "-01-10", 68),
                Arguments.of(2000f, DaysCalculateService.YEAR + "-02-15", DaysCalculateService.YEAR + "-03-01", 750),
                Arguments.of(3000f, DaysCalculateService.YEAR + "-04-01", DaysCalculateService.YEAR + "-04-20", 1535)
        );
    }

    private static Stream<Arguments> provideDataForCalculateSalaryPayWithCountDays() {
        return Stream.of(
                Arguments.of(1000f, 10, 341),
                Arguments.of(2000f, 20, 1365),
                Arguments.of(3000f, 30, 3071)
        );
    }

    @ParameterizedTest
    @MethodSource("provideDataForCalculateSalaryPay")
    void testCalculateSalaryPay(Float avgSalary, String startDate, String endDate, Integer expectedSalary) {
        VacationSalaryResponseDto result = salaryCalculatorService.calculateSalaryPay(avgSalary, startDate, endDate);
        assertEquals(expectedSalary, result.getVacationPay());
    }

    @ParameterizedTest
    @MethodSource("provideDataForCalculateSalaryPayWithCountDays")
    void testCalculateSalaryPayWithCountDays(Float avgSalary, Integer countDays, Integer expectedSalary) {
        VacationSalaryResponseDto result = salaryCalculatorService.calculateSalaryPay(avgSalary, countDays);
        assertEquals(expectedSalary, result.getVacationPay());
    }

}