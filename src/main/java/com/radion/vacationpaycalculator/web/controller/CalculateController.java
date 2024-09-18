package com.radion.vacationpaycalculator.web.controller;

import com.radion.vacationpaycalculator.exception.FieldException;
import com.radion.vacationpaycalculator.model.VacationSalaryResponseDto;
import com.radion.vacationpaycalculator.service.SalaryCalculatorService;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;


@RestController
@AllArgsConstructor
@RequestMapping("/api/")
public class CalculateController {

    private MessageSource messageSource;
    private SalaryCalculatorService salaryCalculatorService;

    @GetMapping("/calculate")
    public ResponseEntity<VacationSalaryResponseDto> calculate(
            @RequestParam(name = "avgSalary") Float avgSalary,
            @RequestParam(name = "countDays", required = false) Integer countDays,
            @RequestParam(name = "startVacation", required = false) String startVacation,
            @RequestParam(name = "endVacation", required = false) String endVacation
    ){
        if (startVacation == null || endVacation == null) {
            if (countDays == null || countDays < 0) throw new FieldException(messageSource.getMessage("field.error", null, Locale.getDefault()));
            return ResponseEntity.ok(salaryCalculatorService.calculateSalaryPay(avgSalary, countDays));
        }else {
            return ResponseEntity.ok(salaryCalculatorService
                    .calculateSalaryPay(avgSalary, startVacation, endVacation));
        }
    }
}
