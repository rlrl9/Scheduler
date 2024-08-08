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
//        return new ResponseEntity<>(
//                "schedule created",HttpStatus.CREATED);
        scheduleService.registerSchedule(scheduleDTO);
    }
//    //스케줄 상세
    @GetMapping("/{id}")
    public ScheduleDTO ShowSchedule(@PathVariable("id") Long id) throws IOException {
        return scheduleService.showSchedule(id);
    }

    //월별 조회
//    @GetMapping
//    public List<ScheduleDTO> ShowScheduleMonthly(@RequestParam(value = "month", required = true) String month) throws IOException {
//        SelectScheduleDTO sld = new SelectScheduleDTO();
//        sld.setMonth(month);
//        sld.setWeek(null);
//        return scheduleService.selectSchedules(sld);
//    }

    //주별/월별 조회, 전체 조회
    @GetMapping("/view")
    public List<ScheduleDTO> ShowScheduleWeekly(
            @RequestParam(value = "month", required = false) String month,
            @RequestParam(value = "week", required = false) Integer week) throws IOException {
        SelectScheduleDTO sld = new SelectScheduleDTO();
        sld.setMonth(month);
        sld.setWeek(week);
        return scheduleService.selectSchedules(sld);
    }
}
