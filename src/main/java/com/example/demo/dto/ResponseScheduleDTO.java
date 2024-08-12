package com.example.demo.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Data
public class ResponseScheduleDTO {
    private int id;
    private String title;
    private String content;
    private String color;
    private String repeat;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime startT;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime endT;
    private String fileUrls;
    public List<String> getFileUrls() {
        if (fileUrls != null && !fileUrls.isEmpty()) {
            return Arrays.asList(fileUrls.split(","));
        } else {
            return Collections.emptyList();
        }
    }
}