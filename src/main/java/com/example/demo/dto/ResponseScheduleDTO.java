package com.example.demo.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

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
    private String alarm;
    public List<String> getFileUrls() {
        if (fileUrls != null && !fileUrls.isEmpty()) {
            return Arrays.asList(fileUrls.split(","));
        } else {
            return Collections.emptyList();
        }
    }

    //알람 표시
    public String getAlarm(){
        String msg="";
        LocalDateTime now = LocalDateTime.now();
        if (repeat.equals("매일")){
            int hour = startT.getHour() - now.getHour();
            int min = startT.getMinute() - now.getMinute();
            if (hour>=0 && min>=0){
                msg = hour + "시간 " + min + "분 전입니다.";
            }else {
                msg = (24 + hour) + "시간 " + (60 + min) + "분 전입니다.";
            }
        }
        else if (repeat.equals("매주")){
            long days = DAYS.between(now,startT);
            int hour = startT.getHour() - now.getHour();
//            int day = hour/24;
//            hour = hour%24;
            int min = startT.getMinute() - now.getMinute();
            if (days>=0 && hour>=0 && min>=0){
                msg = days + "일 " + hour + "시간 " + min + "분 전입니다.";
            }else{
                msg = (7 + days) + "일 " + (24 + hour) + "시간 " + (60 + min) + "분 전입니다.";
            }
        }
        else if (repeat.equals("매월")){
            long days = DAYS.between(startT,now);
            int hour = startT.getHour() - now.getHour();
            int min = startT.getMinute() - now.getMinute();
            if (days>=0 && hour>=0 && min>=0){
                msg = days + "일 " + hour + "시간 " + min + "분 전입니다.";
            }else{
                msg = (30 + days) + "일 " + (24 + hour) + "시간 " + (60 + min) + "분 전입니다.";
            }
        }
        return msg;
    }
}