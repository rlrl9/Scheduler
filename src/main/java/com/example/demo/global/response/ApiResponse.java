package com.example.demo.global.response;

import com.example.demo.global.exception.ExceptionInfo;
import com.example.demo.schedule.success.ScheduleSuccessInfo;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApiResponse <T>{

    private int status;
    private T data;
    private String code;
    private String message;

    public static <T> ApiResponse<T> successResponse(T data, ScheduleSuccessInfo scheduleSuccessInfo) {
        return new ApiResponse<>(HttpStatus.OK.value(), data, scheduleSuccessInfo.getCode(), scheduleSuccessInfo.getMessage());
    }

    public static ApiResponse<?> successWithNoContent(ScheduleSuccessInfo scheduleSuccessInfo) {
        return new ApiResponse<>(HttpStatus.OK.value(), null, scheduleSuccessInfo.getCode(), scheduleSuccessInfo.getMessage());
    }

    public static ApiResponse<?> errorResponse(ExceptionInfo exceptionInfo){
        return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), null, exceptionInfo.getCode(), exceptionInfo.getMessage());
    }

    public static ApiResponse<?> errorResponse(String errorMessage){
        return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), null, null, errorMessage);
    }

}
