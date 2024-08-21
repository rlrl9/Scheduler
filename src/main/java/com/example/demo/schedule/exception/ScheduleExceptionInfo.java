package com.example.demo.schedule.exception;

import com.example.demo.global.exception.ExceptionInfo;
import lombok.Getter;

@Getter
public enum ScheduleExceptionInfo implements ExceptionInfo {

    NOT_EXIST_SCHEDULE("NOT_EXIST_SCHEDULE","존재하지 않는 스케줄입니다.");

    private final String code;
    private final String message;

    ScheduleExceptionInfo(String code, String message){
        this.code = code;
        this.message = message;
    }
}
