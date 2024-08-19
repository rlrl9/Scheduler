package com.example.demo.mapper;
import com.example.demo.dto.FileDTO;
import com.example.demo.dto.RequestScheduleDTO;
import com.example.demo.dto.ResponseScheduleDTO;
import com.example.demo.dto.SelectScheduleDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ScheduleMapper {

    //스케줄 등록
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertSchedule(RequestScheduleDTO requestScheduleDTO);

    //스케줄 상세 조회
    Optional<ResponseScheduleDTO> selectById(Long id);

    //스케줄 검색
    List<ResponseScheduleDTO> selectSchedules(SelectScheduleDTO sld);

    //이미지 업로드
    void insertUploadImage(FileDTO fileDTO);

    //스케줄 삭제
    void deleteSchedule(Long id);

    //이미지 삭제
    void deleteImageByPostId(Long id);

    //스케줄 수정
    void patchSchedule(RequestScheduleDTO requestScheduleDTO);

    //스케줄 존재하는 지 확인
//    Optional<Integer> findById(Long id);
}
