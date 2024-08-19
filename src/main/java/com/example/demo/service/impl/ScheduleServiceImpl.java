package com.example.demo.service.impl;

import com.example.demo.dto.FileDTO;
import com.example.demo.dto.RequestScheduleDTO;
import com.example.demo.dto.ResponseScheduleDTO;
import com.example.demo.dto.SelectScheduleDTO;
import com.example.demo.mapper.ScheduleMapper;
import com.example.demo.schedule.exception.ScheduleBusinessException;
import com.example.demo.schedule.exception.ScheduleExceptionInfo;
import com.example.demo.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * 스케줄 서비스 구현
 */
@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleMapper scheduleMapper;

    /**
     * 첨부파일과 함께 스케줄 등록
     *
     * @param requestScheduleDTO
     * return
     */
    @Transactional
    @Override
    public ResponseScheduleDTO insertSchedule(RequestScheduleDTO requestScheduleDTO) {
        //스케줄 등록
        scheduleMapper.insertSchedule(requestScheduleDTO);
        //이미지 등록
        if (requestScheduleDTO.getUploadImageFiles() != null) {
            MultipartFile[] uploadImageFiles = requestScheduleDTO.getUploadImageFiles();
            FileDTO fileDTO = new FileDTO();
            String path = System.getProperty("user.dir") + "/src/main/resources/static/images/";
            fileDTO.setPostId(requestScheduleDTO.getId());
            for (MultipartFile mfile : uploadImageFiles) {
                String filename = UUID.randomUUID().toString();
                try {
                    String originalFilename = mfile.getOriginalFilename();
                    String contentType = mfile.getContentType();
                    String fileExtension = "." + (contentType.substring(contentType.indexOf("/") + 1));

                    File filepath = new File(path + filename + fileExtension);
                    mfile.transferTo(filepath);

                    fileDTO.setFilename(originalFilename.substring(0, originalFilename.indexOf(".")));
                    fileDTO.setFileExtension(fileExtension);
                    fileDTO.setFileUrl("/images/" + filename + fileExtension);
                    scheduleMapper.insertUploadImage(fileDTO);
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
        return scheduleMapper.selectById(requestScheduleDTO.getId()).orElse(null);
    }

    /**
     * 단일 스케줄 조회
     *
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    public Optional<ResponseScheduleDTO> selectById(Long id) {
        return scheduleMapper.selectById(id);
    }

    /**
     * 조건에 따른 스케줄 리스트 조회
     *
     * @param month
     * @param week
     * @param color
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    public List<ResponseScheduleDTO> selectSchedules(Integer month, Integer week, String color) {
        SelectScheduleDTO sld = new SelectScheduleDTO(month, week, color);
        return scheduleMapper.selectSchedules(sld);
    }

    /**
     * 스케줄 삭제
     *
     * @param id
     */
    @Transactional
    @Override
    public void deleteSchedule(Long id) {
        //이미지 삭제
        scheduleMapper.deleteImageByPostId(id);
        //스케줄 삭제
        scheduleMapper.deleteSchedule(id);
    }

    /**
     * 스케줄 수정
     *
     * @param id
     * @param requestScheduleDTO
     * @return
     */
    @Transactional
    @Override
    public ResponseScheduleDTO patchSchedule(Long id, RequestScheduleDTO requestScheduleDTO) {
        //스케줄 조회 후 존재할 시 수정, 기존 이미지 전부 삭제
        ResponseScheduleDTO i = scheduleMapper.selectById(id)
                .orElseThrow(() -> new ScheduleBusinessException(ScheduleExceptionInfo.NOT_EXIST_SCHEDULE));
        requestScheduleDTO.setId(id);
        scheduleMapper.patchSchedule(requestScheduleDTO);
        scheduleMapper.deleteImageByPostId(id);
        //이미지 등록
        if (requestScheduleDTO.getUploadImageFiles() != null) {
            MultipartFile[] uploadImageFiles = requestScheduleDTO.getUploadImageFiles();
            FileDTO fileDTO = new FileDTO();
            String path = System.getProperty("user.dir") + "/src/main/resources/static/images/";
            fileDTO.setPostId(id);
            for (MultipartFile mfile : uploadImageFiles) {
                String filename = UUID.randomUUID().toString();
                try {
                    String originalFilename = mfile.getOriginalFilename();
                    String contentType = mfile.getContentType();
                    String fileExtension = "." + (contentType.substring(contentType.indexOf("/") + 1));

                    File filepath = new File(path + filename + fileExtension);
                    mfile.transferTo(filepath);

                    fileDTO.setFilename(originalFilename.substring(0, originalFilename.indexOf(".")));
                    fileDTO.setFileExtension(fileExtension);
                    fileDTO.setFileUrl("/images/" + filename + fileExtension);

                    scheduleMapper.insertUploadImage(fileDTO);
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
        return scheduleMapper.selectById(id).orElse(null);
    }
}