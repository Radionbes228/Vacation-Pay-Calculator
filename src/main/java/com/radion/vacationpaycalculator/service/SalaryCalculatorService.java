package com.radion.vacationpaycalculator.service;

import com.radion.vacationpaycalculator.exception.DateFormatException;
import com.radion.vacationpaycalculator.exception.InvalidRangeDateException;
import com.radion.vacationpaycalculator.model.VacationSalaryResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeParseException;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class SalaryCalculatorService {
    private final MessageSource messageSource;

    private final DaysCalculateService daysCalculateService;

    public VacationSalaryResponseDto calculateSalaryPay(Float avgSalary, Integer countDays) {
        Integer res = (int) ((avgSalary / DaysCalculateService.AVG_DAY_OF_MONTH) * countDays);
        return new VacationSalaryResponseDto(res);
    }

    public VacationSalaryResponseDto calculateSalaryPay(Float avgSalary, String startDate, String endDate) {
        try {
            Integer salary = (int) ((avgSalary / DaysCalculateService.AVG_DAY_OF_MONTH) * daysCalculateService
                    .getCountWorkDays(startDate, endDate));
            return new VacationSalaryResponseDto(salary);
        } catch (DateTimeParseException e) {
            throw new DateFormatException(messageSource.getMessage("parse.error", null, Locale.getDefault()));
        }catch (IllegalArgumentException e){
            throw new InvalidRangeDateException(messageSource.getMessage("range.date.error", null, Locale.getDefault()));
        }
    }
}
