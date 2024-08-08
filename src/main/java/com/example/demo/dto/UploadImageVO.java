package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@AllArgsConstructor
public class UploadImageVO {
    private MultipartFile[] uploadImageFiles;
}
