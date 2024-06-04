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
@RequestMapping("/crawl")
@RequiredArgsConstructor
public class CrawlingController {

    private final CrawlingService crawlingService;

    @PostMapping("")
    public ResponseEntity<CrawlingDto> crawling() throws IOException {
        CrawlingDto crawlingDto  = crawlingService.crawl();
        return ResponseEntity.ok(crawlingDto);

    }

    @GetMapping("/{crawlingId}")
    public ResponseEntity<CrawlingDto> sendCrawling(@PathVariable long crawlingId) {
        CrawlingDto crawlingDto = crawlingService.findById(crawlingId);
        return ResponseEntity.ok(crawlingDto);
    }

    @GetMapping("/latest")
    public ResponseEntity<CrawlingDto> getLatestCrawlingData() {
        CrawlingDto crawlingDto = crawlingService.getLatestCrawlingData();
        return ResponseEntity.ok(crawlingDto);
    }
}
