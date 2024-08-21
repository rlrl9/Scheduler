package com.example.demo.global.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private final ExceptionInfo exceptionInfo;

    public BusinessException(ExceptionInfo exceptionInfo) {
        super(exceptionInfo.getMessage());
        this.exceptionInfo = exceptionInfo;
    }

}
