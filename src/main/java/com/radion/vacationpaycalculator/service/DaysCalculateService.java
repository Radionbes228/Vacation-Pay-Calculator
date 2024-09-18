package com.radion.vacationpaycalculator.service;

import lombok.Getter;
import org.springframework.stereotype.Service;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Stream;


@Service
@Getter
public class DaysCalculateService {
    public static final Float AVG_DAY_OF_MONTH = 29.3f;
    public static final Integer YEAR = LocalDate.now().getYear();
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private static final List<LocalDate> FIXED_HOLIDAYS = List.of(
            LocalDate.of(YEAR, 1, 1),
            LocalDate.of(YEAR, 1, 2),
            LocalDate.of(YEAR, 1, 3),
            LocalDate.of(YEAR, 1, 4),
            LocalDate.of(YEAR, 1, 5),
            LocalDate.of(YEAR, 1, 6),
            LocalDate.of(YEAR, 1, 7),
            LocalDate.of(YEAR, 1, 8),
            LocalDate.of(YEAR, 2, 23),
            LocalDate.of(YEAR, 3, 8),
            LocalDate.of(YEAR, 5, 1),
            LocalDate.of(YEAR, 5, 9),
            LocalDate.of(YEAR, 6, 12),
            LocalDate.of(YEAR, 11, 4),
            LocalDate.of(YEAR, 12, 31)
    );

    public int getCountWorkDays(String startDate, String endDay) throws DateTimeParseException, IllegalArgumentException {
        if (startDate.equals(endDay)) return 0;

        var start = LocalDate.parse(startDate, dateTimeFormatter);
        var end = LocalDate.parse(endDay, dateTimeFormatter);

        Stream.iterate(start, d -> d.plusDays(1))
                .limit(ChronoUnit.DAYS.between(start, end) + 1)
                .filter(this::isNotHoliday)
                .filter(this::isWorkingDay)
                .forEach(System.out::println);

        return (int)  Stream.iterate(start, d -> d.plusDays(1))
                .limit(ChronoUnit.DAYS.between(start, end) + 1)
                .filter(this::isNotHoliday)
                .filter(this::isWorkingDay)
                .count();
    }

    public boolean isNotHoliday(LocalDate date){
        return !FIXED_HOLIDAYS.contains(date);
    }

    public boolean isWorkingDay(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return !(dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY);
    }
}
