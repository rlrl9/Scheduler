package com.example.demo.service;

import com.example.demo.dto.FileDTO;
import com.example.demo.dto.ScheduleDTO;
import com.example.demo.dto.SelectScheduleDTO;
import com.example.demo.mapper.ScheduleMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ScheduleService {
    private final ScheduleMapper scheduleMapper;

    public ScheduleService(ScheduleMapper scheduleMapper) {
        this.scheduleMapper = scheduleMapper;
    }

    public void registerSchedule(ScheduleDTO scheduleDTO) {
        scheduleMapper.registerSchedule(scheduleDTO);
        if (scheduleDTO.getUploadImageFiles() != null) {
            MultipartFile[] uploadImageFiles = scheduleDTO.getUploadImageFiles();
            FileDTO fileDTO = new FileDTO();
            String path = System.getProperty("user.dir") + "/src/main/resources/static/images/";
            fileDTO.setPostId(scheduleDTO.getId());
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

    public ScheduleDTO showSchedule(Long id) {
        return scheduleMapper.showSchedule(id);
    }

    public List<ScheduleDTO> selectSchedules(SelectScheduleDTO sld) {
        return scheduleMapper.selectSchedules(sld);
    }
}