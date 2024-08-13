package com.example.demo.controller;

import com.example.demo.dto.RequestScheduleDTO;
import com.example.demo.dto.ResponseScheduleDTO;
import com.example.demo.dto.SelectScheduleDTO;
import com.example.demo.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    //스케줄 등록
    @PostMapping
    public ResponseEntity<String> RegisterSchedule(RequestScheduleDTO requestScheduleDTO) {
        scheduleService.registerSchedule(requestScheduleDTO);
        return new ResponseEntity<>("저장 성공", HttpStatus.OK);
    }

    //스케줄 상세
    @GetMapping("/{id}")
    public ResponseEntity<ResponseScheduleDTO> ShowSchedule(@PathVariable("id") Long id) {
        return new ResponseEntity<>(scheduleService.showSchedule(id), HttpStatus.OK);
    }

    //주별/월별 조회, 전체 조회, 색상별 조회
    @GetMapping("/list")
    public ResponseEntity<List<ResponseScheduleDTO>> ShowScheduleWeekly(
            @RequestParam(value = "month", required = false) Integer month,
            @RequestParam(value = "week", required = false) Integer week,
            @RequestParam(value = "color", required = false) String color) {
        return new ResponseEntity<>(scheduleService.selectSchedules(month,week,color), HttpStatus.OK);
    }

    //스케줄 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSchedule(@PathVariable("id") Long id) {
        scheduleService.deleteSchedule(id);
        return new ResponseEntity<>("삭제 성공", HttpStatus.RESET_CONTENT);
    }

    //스케줄 수정
    @PatchMapping("/{id}")
    public ResponseEntity<String> patchSchedule(@PathVariable("id") Long id,
                                       RequestScheduleDTO requestScheduleDTO) {
        scheduleService.patchSchedule(id,requestScheduleDTO);
        return new ResponseEntity<>("수정 성공", HttpStatus.RESET_CONTENT);
    }
}
