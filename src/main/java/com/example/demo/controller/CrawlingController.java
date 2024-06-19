package com.example.demo.controller;

import com.example.demo.dto.CrawlingDto;
import com.example.demo.entity.Crawling;
import com.example.demo.service.CrawlingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CrawlingController {

    private final CrawlingService crawlingService;

    @GetMapping("/crawling")
    public void crawling() {
        crawlingService.crawl();
    }

    @GetMapping("/sendRecentData")
    public CrawlingDto sendRecentData() {
        Crawling crawling = crawlingService.findRecentData();
        return new CrawlingDto(crawling);
    }
}