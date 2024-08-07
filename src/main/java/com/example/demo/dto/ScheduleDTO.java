package com.example.demo.dto;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ScheduleDTO {
    private int id;
    private String title;
    private String content;
    private String color;
    private String repeat;
    private LocalDate time;
}