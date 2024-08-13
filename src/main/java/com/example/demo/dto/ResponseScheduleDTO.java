package com.example.demo.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
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
        long differenceInMillis = Timestamp.valueOf(startT).getTime() - Timestamp.valueOf(now).getTime();
        long days = (differenceInMillis / (24 * 60 * 60 * 1000L)) % 365;
        long hours = (differenceInMillis / (60 * 60 * 1000L)) % 24;
        long minutes = (differenceInMillis / (60 * 1000L)) % 60;
        long seconds = (differenceInMillis / 1000) % 60;
        if (repeat.equals("매일")){
            if (hours>=0 && minutes>=0 && seconds>=0){
                msg = hours + "시간 " + minutes + "분 " + seconds + "초 전입니다.";
            }else {
                hours = 24 + ((differenceInMillis / (60 * 60 * 1000L)) % 24);
                minutes = 60 + ((differenceInMillis / (60 * 1000L)) % 60);
                seconds = 60 + ((differenceInMillis / 1000) % 60);
                msg = hours + "시간 " + minutes + "분 " + seconds + "초 전입니다.";
            }
        }
        else if (repeat.equals("매주")){
            if (days>=0 && hours>=0 && minutes>=0 && seconds>=0){
                msg = days + "일 " + hours + "시간 " + minutes + "분 " + seconds + "초 전입니다.";
            }else{
                int diff = now.getDayOfYear() - startT.getDayOfYear();
                System.out.println("1:"+diff);
                diff = diff/7;
                System.out.println("diff/7:"+diff);
                differenceInMillis = Timestamp.valueOf(startT.plusWeeks(diff+1)).getTime() - Timestamp.valueOf(now).getTime();
                days = (differenceInMillis / (24 * 60 * 60 * 1000L)) % 365;
                hours = (differenceInMillis / (60 * 60 * 1000L)) % 24;
                minutes = (differenceInMillis / (60 * 1000L)) % 60;
                seconds = (differenceInMillis / 1000) % 60;
                msg = days + "일 " + hours + "시간 " + minutes + "분 " + seconds + "초 전입니다.";
            }
        }
        else if (repeat.equals("매월")){
            if (days>=0 && hours>=0 && minutes>=0 && seconds>=0){
                msg = days + "일 " + hours + "시간 " + minutes + "분 " + seconds + "초 전입니다.";
            }else{
                differenceInMillis = Timestamp.valueOf(startT.plusMonths(1)).getTime() - Timestamp.valueOf(now).getTime();
                days = (differenceInMillis / (24 * 60 * 60 * 1000L)) % 365;
                hours = (differenceInMillis / (60 * 60 * 1000L)) % 24;
                minutes = (differenceInMillis / (60 * 1000L)) % 60;
                seconds = (differenceInMillis / 1000) % 60;
                msg = days + "일 " + hours + "시간 " + minutes + "분 " + seconds + "초 전입니다.";
            }
        }
        return msg;
    }
}