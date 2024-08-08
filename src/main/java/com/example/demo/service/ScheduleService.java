package com.example.demo.service;

import com.example.demo.dto.ScheduleDTO;
import com.example.demo.dto.SelectScheduleDTO;
import com.example.demo.mapper.ScheduleMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ScheduleService {
    private final ScheduleMapper scheduleMapper;

    public ScheduleService(ScheduleMapper scheduleMapper) {
        this.scheduleMapper = scheduleMapper;
    }

    public void registerSchedule(ScheduleDTO scheduleDTO) {
        scheduleMapper.registerSchedule(scheduleDTO);
    }

    public ScheduleDTO showSchedule(Long id) {
        return scheduleMapper.showSchedule(id);
    }

    public List<ScheduleDTO> selectSchedules(SelectScheduleDTO sld) {
        return scheduleMapper.selectSchedules(sld);
    }
}