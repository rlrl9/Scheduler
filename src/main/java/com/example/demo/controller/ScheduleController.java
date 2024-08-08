package com.example.demo.controller;

import com.example.demo.dto.ScheduleDTO;
import com.example.demo.dto.SelectScheduleDTO;
import com.example.demo.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedule")
public class ScheduleController {
    private final ScheduleService scheduleService;

    //스케줄 등록
    @PostMapping(produces = "application/json; charset=utf-8")
    public void RegisterSchedule(@RequestBody ScheduleDTO scheduleDTO) throws
            IOException {
        scheduleService.registerSchedule(scheduleDTO);
    }
    //스케줄 상세
    @GetMapping("/{id}")
    public ScheduleDTO ShowSchedule(@PathVariable("id") Long id) throws IOException {
        return scheduleService.showSchedule(id);
    }

    //주별/월별 조회, 전체 조회
    @GetMapping("/view")
    public List<ScheduleDTO> ShowScheduleWeekly(
            @RequestParam(value = "month", required = false) Integer month,
            @RequestParam(value = "week", required = false) Integer week) throws IOException {
        SelectScheduleDTO sld = new SelectScheduleDTO(month,week);
        return scheduleService.selectSchedules(sld);
    }
}
