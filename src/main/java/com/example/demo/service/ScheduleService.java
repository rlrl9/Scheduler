package com.example.demo.service;

import com.example.demo.dto.RequestScheduleDTO;
import com.example.demo.dto.ResponseScheduleDTO;

import java.util.List;
/**
 * 스케줄 서비스 인터페이스
 */
public interface ScheduleService {

    void registerSchedule(RequestScheduleDTO requestScheduleDTO);

    ResponseScheduleDTO showSchedule(Long id);

    List<ResponseScheduleDTO> selectSchedules(Integer month, Integer week, String color);

    void deleteSchedule(Long id);

    void patchSchedule(Long id,RequestScheduleDTO requestScheduleDTO);

}
