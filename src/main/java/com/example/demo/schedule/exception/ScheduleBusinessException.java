package com.example.demo.schedule.exception;

import com.example.demo.global.exception.BusinessException;
import com.example.demo.global.exception.ExceptionInfo;
import lombok.Getter;

@Getter
public class ScheduleBusinessException extends BusinessException {
    public ScheduleBusinessException(ExceptionInfo exceptionInfo){
        super(exceptionInfo);
    }
}
