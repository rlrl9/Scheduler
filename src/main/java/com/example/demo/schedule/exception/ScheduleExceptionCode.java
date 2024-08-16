package com.example.demo.schedule.exception;

import com.example.demo.global.exception.ExceptionCode;
import lombok.Getter;

@Getter
public enum ScheduleExceptionCode implements ExceptionCode {

    NOT_EXIST_SCHEDULE("NOT_EXIST_SCHEDULE","존재하지 않는 스케줄입니다.");

    private final String code;
    private final String message;

    ScheduleExceptionCode(String code, String message){
        this.code = code;
        this.message = message;
    }
}
