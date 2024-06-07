package com.example.demo.controller;

import com.example.demo.dto.CrawlingDto;
import com.example.demo.entity.Crawling;
import com.example.demo.service.CrawlingService;
import jakarta.ws.rs.GET;
import lombok.RequiredArgsConstructor;
import org.bouncycastle.crypto.engines.CramerShoupCiphertext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CrawlingController {

    private final CrawlingService crawlingService;

    @GetMapping ("/crawling")
    public void crawling() {
        crawlingService.crawl();
    }

    @GetMapping("/sendRecentData")
    public CrawlingDto sendRecentData() {
        Crawling crawling = crawlingService.findRecentData();
        return new CrawlingDto(crawling);
    }
}