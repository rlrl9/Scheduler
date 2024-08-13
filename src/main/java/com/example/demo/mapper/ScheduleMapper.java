package com.example.demo.mapper;
import com.example.demo.dto.FileDTO;
import com.example.demo.dto.RequestScheduleDTO;
import com.example.demo.dto.ResponseScheduleDTO;
import com.example.demo.dto.SelectScheduleDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import java.util.List;

@Mapper
public interface ScheduleMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    void registerSchedule(RequestScheduleDTO requestScheduleDTO);

    ResponseScheduleDTO showSchedule(Long id);

    List<ResponseScheduleDTO> selectSchedules(SelectScheduleDTO sld);

    void insertUploadImage(FileDTO fileDTO);

    void deleteSchedule(Long id);

    void deleteImage(Long id);

    void patchSchedule(RequestScheduleDTO requestScheduleDTO);

    Integer ifSchedule(int id);
}
