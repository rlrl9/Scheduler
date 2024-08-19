package com.example.demo.service;

import com.example.demo.dto.RequestScheduleDTO;
import com.example.demo.dto.ResponseScheduleDTO;

import java.util.List;
import java.util.Optional;

/**
 * 스케줄 서비스 인터페이스
 */
public interface ScheduleService {

    ResponseScheduleDTO insertSchedule(RequestScheduleDTO requestScheduleDTO);

    Optional<ResponseScheduleDTO> selectById(Long id);

    List<ResponseScheduleDTO> selectSchedules(Integer month, Integer week, String color);

    void deleteSchedule(Long id);

    ResponseScheduleDTO patchSchedule(Long id,RequestScheduleDTO requestScheduleDTO);

}
