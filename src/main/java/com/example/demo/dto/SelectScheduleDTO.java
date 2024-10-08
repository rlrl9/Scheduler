package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Locale;

@Getter
@Setter
public class SelectScheduleDTO {
    private Integer month; // 스케줄 조회 월
    private Integer week; // 스케줄 조회 주
    private Integer totalWeek; // 월, 주를 통해 1년 중 몇 번째 주인지
    private String color; // 색상

    public SelectScheduleDTO(Integer month, Integer week, String color) {
        this.month = month;
        this.week = week;
        this.color = color;
        if (month != null && week != null) {
            this.totalWeek = getTotalWeekly(month, week);
        }
    }

    //1년으로 따졌을 때 해당 월의 해당 주차가 총 몇 주차인지 계산하는 함수
    public static Integer getTotalWeekly(Integer month, Integer week) {

        //2024년이라고 가정
        int year = 2024;

        LocalDate startOfMarch = LocalDate.of(year, month, 1);
        LocalDate startOfThirdWeek = startOfMarch.plusWeeks(week - 1);

        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        int weekNumber = startOfThirdWeek.get(weekFields.weekOfWeekBasedYear());

        return weekNumber;
    }

}
