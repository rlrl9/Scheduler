package com.example.demo.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Data
public class ResponseScheduleDTO {
    private int id; // 스케줄 아이디
    private String title; //스케줄 제목
    private String content; // 스케줄 내용
    private String color; // 스케줄 색상
    private String repeat; // 반복 설정 (매월/매주/매일 중 택1)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime startT; // 스케줄 시작시
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime endT; // 스케줄 종료시
    private String fileUrls; // 이미지 파일 url 목록
    private String alarm; // 스케줄 알람이 울리기까지 남은 시간

    //fileUrls 리턴
    public List<String> getFileUrls() {
        if (fileUrls != null && !fileUrls.isEmpty()) {
            return Arrays.asList(fileUrls.split(","));
        } else {
            return Collections.emptyList();
        }
    }

    //알람 표시 (alarm 리턴)
    public String getAlarm() {
        String msg = "";
        LocalDateTime now = LocalDateTime.now(); //현재시
        long differenceInMillis = Timestamp.valueOf(startT).getTime() - Timestamp.valueOf(now).getTime();
        long days = (differenceInMillis / (24 * 60 * 60 * 1000L)) % 365;
        long hours = (differenceInMillis / (60 * 60 * 1000L)) % 24;
        long minutes = (differenceInMillis / (60 * 1000L)) % 60;
        long seconds = (differenceInMillis / 1000) % 60;
        switch (repeat) {
            case "매일":
                if (differenceInMillis < 0) {
                    if (minutes <= 0 || seconds <= 0) {
                        hours = 23 + ((differenceInMillis / (60 * 60 * 1000L)) % 24);
                    } else {
                        hours = 24 + ((differenceInMillis / (60 * 60 * 1000L)) % 24);
                    }
                    if (seconds <= 0) {
                        minutes = 59 + ((differenceInMillis / (60 * 1000L)) % 60);
                    } else {
                        minutes = 60 + ((differenceInMillis / (60 * 1000L)) % 60);
                    }
                    seconds = 60 + ((differenceInMillis / 1000) % 60);
                }
                msg = hours + "시간 " + minutes + "분 " + seconds + "초 전입니다.";
                break;
            case "매주":
                if (differenceInMillis < 0) {
                    int diff = now.getDayOfYear() - startT.getDayOfYear();
                    diff = diff / 7;
                    differenceInMillis = Timestamp.valueOf(startT.plusWeeks(diff + 1)).getTime() - Timestamp.valueOf(now).getTime();
                    days = (differenceInMillis / (24 * 60 * 60 * 1000L)) % 365;
                    hours = (differenceInMillis / (60 * 60 * 1000L)) % 24;
                    minutes = (differenceInMillis / (60 * 1000L)) % 60;
                    seconds = (differenceInMillis / 1000) % 60;
                }
                msg = days + "일 " + hours + "시간 " + minutes + "분 " + seconds + "초 전입니다.";
                break;
            case "매월":
                if (differenceInMillis < 0) {
                    int diff = now.getMonthValue() - startT.getMonthValue();
                    long diffMills = Timestamp.valueOf(now).getTime() - Timestamp.valueOf(startT.plusMonths(diff)).getTime();
                    if (diffMills > 0) {
                        differenceInMillis = Timestamp.valueOf(startT.plusMonths(diff + 1)).getTime() - Timestamp.valueOf(now).getTime();
                    } else {
                        differenceInMillis = Timestamp.valueOf(startT.plusMonths(diff)).getTime() - Timestamp.valueOf(now).getTime();
                    }
                    days = (differenceInMillis / (24 * 60 * 60 * 1000L)) % 365;
                    hours = (differenceInMillis / (60 * 60 * 1000L)) % 24;
                    minutes = (differenceInMillis / (60 * 1000L)) % 60;
                    seconds = (differenceInMillis / 1000) % 60;
                }
                msg = days + "일 " + hours + "시간 " + minutes + "분 " + seconds + "초 전입니다.";
                break;
        }
        return msg;
    }
}