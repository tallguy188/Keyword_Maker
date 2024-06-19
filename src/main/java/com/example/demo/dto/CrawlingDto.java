package com.example.demo.dto;

import com.example.demo.entity.Crawling;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CrawlingDto {
    private long rawDataId;
    private String rawData;
    private LocalDateTime createdTime;

    public CrawlingDto(Crawling crawling) {
        this.rawDataId = crawling.getCrawlingId();
        this.rawData = crawling.getRawData();
        this.createdTime = crawling.getCreatedTime();
    }
}