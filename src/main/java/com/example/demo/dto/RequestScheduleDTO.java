package com.example.demo.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
public class RequestScheduleDTO {
    private Long id; // 스케줄 아이디
    private String title; //스케줄 제목
    private String content; // 스케줄 내용
    private String color; // 스케줄 색상
    private String repeat; // 반복 설정 (매월/매주/매일 중 택1)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime startT; // 스케줄 시작시
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime endT; // 스케줄 종료시
    private MultipartFile[] uploadImageFiles; // 업로드 이미지 파일들
}