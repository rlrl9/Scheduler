package com.example.demo.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class FileDTO {
    private int fileId;
    private int postId;
    private String filename;
    private String fileExtension;
    private String fileUrl;
}
