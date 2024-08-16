package com.example.demo.schedule.exception;

import com.example.demo.global.exception.BusinessException;
import com.example.demo.global.exception.ExceptionCode;
import lombok.Getter;

@Getter
public class ScheduleBussinessException extends BusinessException {
    public ScheduleBussinessException(ExceptionCode exceptionCode){
        super(exceptionCode);
    }
}
