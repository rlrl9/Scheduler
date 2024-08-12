package com.example.demo.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
public class RequestScheduleDTO {
    private int id;
    private String title;
    private String content;
    private String color;
    private String repeat;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime startT;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime endT;
    private MultipartFile[] uploadImageFiles;
}