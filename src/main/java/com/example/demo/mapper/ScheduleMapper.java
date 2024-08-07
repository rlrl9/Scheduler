package com.example.demo.mapper;
import com.example.demo.dto.ScheduleDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ScheduleMapper {
    void registerSchedule(ScheduleDTO scheduleDTO);
    ScheduleDTO showSchedule(Long id);
}
