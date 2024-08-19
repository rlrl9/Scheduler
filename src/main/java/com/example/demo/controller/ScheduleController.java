package com.example.demo.controller;

import com.example.demo.dto.RequestScheduleDTO;
import com.example.demo.dto.ResponseScheduleDTO;
import com.example.demo.schedule.success.ScheduleSuccessInfo;
import com.example.demo.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

import com.example.demo.global.response.ApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules")
public class ScheduleController {
    private final ScheduleService scheduleService;

    //스케줄 등록
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<?>> registerSchedule(RequestScheduleDTO requestScheduleDTO) {
        ResponseScheduleDTO rpDTO = scheduleService.insertSchedule(requestScheduleDTO);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.successResponse(rpDTO, ScheduleSuccessInfo.SUCCESSFULLY_INSERT_SCHEDULE));
    }

    //스케줄 상세
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<Optional<ResponseScheduleDTO>>> showSchedule(@PathVariable("id") Long id) {
        Optional<ResponseScheduleDTO> rpDTO = scheduleService.selectById(id);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.successResponse(rpDTO, ScheduleSuccessInfo.SUCCESSFULLY_SELECT_SCHEDULE));
    }

    //주별/월별 조회, 전체 조회, 색상별 조회
    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<List<ResponseScheduleDTO>>> showScheduleWeekly(
            @RequestParam(value = "month", required = false) Integer month,
            @RequestParam(value = "week", required = false) Integer week,
            @RequestParam(value = "color", required = false) String color) {
        List<ResponseScheduleDTO> lrpDTO = scheduleService.selectSchedules(month,week,color);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.successResponse(lrpDTO, ScheduleSuccessInfo.SUCCESSFULLY_SELECT_SCHEDULE));
    }

    //스케줄 삭제
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<?>> deleteSchedule(@PathVariable("id") Long id) {
        scheduleService.deleteSchedule(id);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.successWithNoContent(ScheduleSuccessInfo.SUCCESSFULLY_DELETE_SCHEDULE));
    }

    //스케줄 수정
    @PatchMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<?>> patchSchedule(@PathVariable("id") Long id,
                                       RequestScheduleDTO requestScheduleDTO) {
        ResponseScheduleDTO rpDTO = scheduleService.patchSchedule(id,requestScheduleDTO);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.successResponse(rpDTO, ScheduleSuccessInfo.SUCCESSFULLY_PATCH_SCHEDULE));
    }
}
