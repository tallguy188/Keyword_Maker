package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CrawlingDto {
    private String rawData;
    private LocalDateTime createdTime;
    private long rawDataId;
}
