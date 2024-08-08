package com.example.demo.mapper;
import com.example.demo.dto.ScheduleDTO;
import com.example.demo.dto.SelectScheduleDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ScheduleMapper {
    void registerSchedule(ScheduleDTO scheduleDTO);
    ScheduleDTO showSchedule(Long id);
    List<ScheduleDTO> selectSchedules(SelectScheduleDTO sld);
}
