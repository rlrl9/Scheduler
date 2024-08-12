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
    public void RegisterSchedule(RequestScheduleDTO requestScheduleDTO) {
        scheduleService.registerSchedule(requestScheduleDTO);
    }

    //스케줄 상세
    @GetMapping("/{id}")
    public ResponseEntity ShowSchedule(@PathVariable("id") Long id) {
        return new ResponseEntity<>(scheduleService.showSchedule(id), HttpStatus.OK);
    }

    //주별/월별 조회, 전체 조회
    @GetMapping("/list")
    public ResponseEntity ShowScheduleWeekly(
            @RequestParam(value = "month", required = false) Integer month,
            @RequestParam(value = "week", required = false) Integer week) {
        SelectScheduleDTO sld = new SelectScheduleDTO(month,week);
        return new ResponseEntity<>(scheduleService.selectSchedules(sld), HttpStatus.OK);
    }
}
