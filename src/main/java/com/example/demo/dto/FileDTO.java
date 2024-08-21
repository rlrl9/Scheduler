package com.example.demo.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class FileDTO {
    private Long fileId; // 파일 아이디
    private Long postId; // 스케줄 아이디
    private String filename; // 파일 원래 이름
    private String fileExtension; //파일 확장자
    private String fileUrl; //파일 저장 위치
}
