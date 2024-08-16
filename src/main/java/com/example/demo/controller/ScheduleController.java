package com.example.demo.controller;

import com.example.demo.dto.RequestScheduleDTO;
import com.example.demo.dto.ResponseScheduleDTO;
import com.example.demo.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.example.demo.global.response.ApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    //스케줄 등록
    @PostMapping
    public ResponseEntity<?> RegisterSchedule(RequestScheduleDTO requestScheduleDTO) {
        scheduleService.registerSchedule(requestScheduleDTO);
//        return new ResponseEntity<>("저장 성공", HttpStatus.OK);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.successWithNoContent());
    }

    //스케줄 상세
    @GetMapping("/{id}")
    public ResponseEntity<?> ShowSchedule(@PathVariable("id") Long id) {
//        return new ResponseEntity<>(scheduleService.showSchedule(id), HttpStatus.OK);
        ResponseScheduleDTO rpDTO = scheduleService.showSchedule(id);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.successResponse(rpDTO));
    }

    //주별/월별 조회, 전체 조회, 색상별 조회
    @GetMapping("/list")
    public ResponseEntity<?> ShowScheduleWeekly(
            @RequestParam(value = "month", required = false) Integer month,
            @RequestParam(value = "week", required = false) Integer week,
            @RequestParam(value = "color", required = false) String color) {
//        return new ResponseEntity<>(scheduleService.selectSchedules(month,week,color), HttpStatus.OK);
        List<ResponseScheduleDTO> lrpDTO = scheduleService.selectSchedules(month,week,color);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.successResponse(lrpDTO));
    }

    //스케줄 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSchedule(@PathVariable("id") Long id) {
        scheduleService.deleteSchedule(id);
//        return new ResponseEntity<>("삭제 성공", HttpStatus.RESET_CONTENT);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.successWithNoContent());
    }

    //스케줄 수정
    @PatchMapping("/{id}")
    public ResponseEntity<?> patchSchedule(@PathVariable("id") Long id,
                                       RequestScheduleDTO requestScheduleDTO) {
        scheduleService.patchSchedule(id,requestScheduleDTO);
//        return new ResponseEntity<>("수정 성공", HttpStatus.RESET_CONTENT);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.successWithNoContent());
    }
}
