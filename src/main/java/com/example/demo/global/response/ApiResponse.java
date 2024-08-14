package com.example.demo.global.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApiResponse <T>{
    private static final String SUCCESS_STATUS = "SUCCESS";
    private static final String FAIL_STATUS = "FAIL";
    private static final String ERROR_STATUS = "ERROR";

    private String status;
    private T data;
    private String code;
    private String message;

    public static <T> ApiResponse<T> successResponse(T data) {
        return new ApiResponse<>(SUCCESS_STATUS, data, null, null);
    }

    public static ApiResponse<?> successWithNoContent() {
        return new ApiResponse<>(SUCCESS_STATUS, null, null, null);
    }

    private ApiResponse(String status, T data, String code, String message) {
        this.status = status;
        this.data = data;
        this.code = code;
        this.message = message;
    }
}
