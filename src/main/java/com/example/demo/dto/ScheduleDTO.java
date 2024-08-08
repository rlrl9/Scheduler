package com.example.demo.dto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ScheduleDTO {
    private int id;
    private String title;
    private String content;
    private String color;
    private String repeat;
    private LocalDateTime startT;
    private LocalDateTime endT;
}