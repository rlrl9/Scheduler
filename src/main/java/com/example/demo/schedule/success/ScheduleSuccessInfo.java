package com.example.demo.schedule.success;

import com.example.demo.global.exception.ExceptionInfo;
import com.example.demo.global.success.SuccessInfo;
import lombok.Getter;

@Getter
public enum ScheduleSuccessInfo implements SuccessInfo {
    SUCCESSFULLY_INSERT_SCHEDULE("SUCCESSFULLY_INSERT_SCHEDULE","스케줄을 성공적으로 입력했습니다."),
    SUCCESSFULLY_PATCH_SCHEDULE("SUCCESSFULLY_PATCH_SCHEDULE","스케줄을 성공적으로 수정했습니다."),
    SUCCESSFULLY_SELECT_SCHEDULE("SUCCESSFULLY_SELECT_SCHEDULE","스케줄을 성공적으로 조회했습니다."),
    SUCCESSFULLY_DELETE_SCHEDULE("SUCCESSFULLY_DELETE_SCHEDULE","스케줄을 성공적으로 삭제했습니다.");

    private final String code;
    private final String message;

    ScheduleSuccessInfo(String code, String message){
        this.code = code;
        this.message = message;
    }
}
