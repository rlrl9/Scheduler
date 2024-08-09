package com.example.demo.mapper;
import com.example.demo.dto.FileDTO;
import com.example.demo.dto.ScheduleDTO;
import com.example.demo.dto.SelectScheduleDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import java.util.List;

@Mapper
public interface ScheduleMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    void registerSchedule(ScheduleDTO scheduleDTO);

    ScheduleDTO showSchedule(Long id);

    List<ScheduleDTO> selectSchedules(SelectScheduleDTO sld);

    void insertUploadImage(FileDTO fileDTO);
}
