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
        switch (repeat) {
            case "매일":
                if (differenceInMillis<0) {
                    if(minutes<=0||seconds<=0){
                        hours = 23 + ((differenceInMillis / (60 * 60 * 1000L)) % 24);
                    }else{
                        hours = 24 + ((differenceInMillis / (60 * 60 * 1000L)) % 24);
                    }
                    if(seconds<=0){
                        minutes = 59 + ((differenceInMillis / (60 * 1000L)) % 60);
                    }else{
                        minutes = 60 + ((differenceInMillis / (60 * 1000L)) % 60);
                    }
                    seconds = 60 + ((differenceInMillis / 1000) % 60);
                }
                msg = hours + "시간 " + minutes + "분 " + seconds + "초 전입니다.";
                break;
            case "매주":
                if (differenceInMillis<0) {
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
                if (differenceInMillis<0) {
                    int diff = now.getMonthValue()-startT.getMonthValue();
                    long diffMills = Timestamp.valueOf(now).getTime() - Timestamp.valueOf(startT.plusMonths(diff)).getTime();
                    if(diffMills>0){
                        differenceInMillis = Timestamp.valueOf(startT.plusMonths(diff+1)).getTime() - Timestamp.valueOf(now).getTime();
                    }else{
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