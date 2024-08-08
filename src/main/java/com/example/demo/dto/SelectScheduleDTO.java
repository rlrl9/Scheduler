package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Locale;

@Getter
@Setter
public class SelectScheduleDTO {
    private Integer month;
    private Integer week;
    private Integer totalWeek;
    public SelectScheduleDTO(Integer month, Integer week) {
        this.month = month;
        this.week = week;
        if (month != null && week != null) {
            this.totalWeek = getTotalWeekly(month, week);
        }
    }

    public static Integer getTotalWeekly(Integer month, Integer week) {

        int year = 2024;

        LocalDate startOfMarch = LocalDate.of(year, month, 1);
        LocalDate startOfThirdWeek = startOfMarch.plusWeeks(week - 1);

        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        int weekNumber = startOfThirdWeek.get(weekFields.weekOfWeekBasedYear());

        return weekNumber;
    }

}
