package com.example.demo.service.impl;

import com.example.demo.dto.FileDTO;
import com.example.demo.dto.RequestScheduleDTO;
import com.example.demo.dto.ResponseScheduleDTO;
import com.example.demo.dto.SelectScheduleDTO;
import com.example.demo.mapper.ScheduleMapper;
import com.example.demo.schedule.exception.ScheduleBussinessException;
import com.example.demo.schedule.exception.ScheduleExceptionCode;
import com.example.demo.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * 스케줄 서비스 구현
 */
@Service
@Transactional
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleMapper scheduleMapper;

    /**
     * 첨부파일과 함께 스케줄 등록
     * @param requestScheduleDTO
     */
    @Override
    public void registerSchedule(RequestScheduleDTO requestScheduleDTO) {
        scheduleMapper.registerSchedule(requestScheduleDTO);
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
    }

    /**
     * 단일 스케줄 조회
     * @param id
     * @return
     */
    @Override
    public ResponseScheduleDTO showSchedule(Long id) {
        return scheduleMapper.showSchedule(id);
    }

    /**
     * 조건에 따른 스케줄 리스트 조회
     * @param month
     * @param week
     * @param color
     * @return
     */
    @Override
    public List<ResponseScheduleDTO> selectSchedules(Integer month, Integer week, String color) {
        SelectScheduleDTO sld = new SelectScheduleDTO(month,week,color);
        return scheduleMapper.selectSchedules(sld);
    }

    /**
     * 스케줄 삭제
     * @param id
     */
    @Override
    public void deleteSchedule(Long id) {
        scheduleMapper.deleteSchedule(id);
        scheduleMapper.deleteImage(id);
    }

    /**
     * 스케줄 수정
     * @param id
     * @param requestScheduleDTO
     */
    @Override
    public void patchSchedule(Long id,RequestScheduleDTO requestScheduleDTO) {
        Integer i = scheduleMapper.findById(id).orElseThrow(() -> new ScheduleBussinessException(ScheduleExceptionCode.NOT_EXIST_SCHEDULE));
//        if (scheduleMapper.findById(id)!=null){
            requestScheduleDTO.setId(id.intValue());
            scheduleMapper.patchSchedule(requestScheduleDTO);
            scheduleMapper.deleteImage(id);
            if (requestScheduleDTO.getUploadImageFiles() != null) {
                MultipartFile[] uploadImageFiles = requestScheduleDTO.getUploadImageFiles();
                FileDTO fileDTO = new FileDTO();
                String path = System.getProperty("user.dir") + "/src/main/resources/static/images/";
                fileDTO.setPostId(id.intValue());
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
//        }
    }
}