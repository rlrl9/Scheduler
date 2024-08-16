package com.example.demo.global.exception;

import com.example.demo.global.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ApiResponse> handleBusinessException(BusinessException e){
        log.error("handleBusinessException e.code : {}, e.message : {}", e.getExceptionCode().getCode(), e.getMessage());
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.errorResponse(e.getExceptionCode()));
    }
}
