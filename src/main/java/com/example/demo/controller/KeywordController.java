package com.example.demo.controller;


import com.example.demo.client.CrawlingServerClient;
import com.example.demo.dto.CrawlingDto;
import com.example.demo.service.KeywordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class KeywordController {
    private final CrawlingServerClient crawlingServerClient;
    private final KeywordService keywordService;

    @GetMapping("/receive")
    public CrawlingDto receiveData() {
        CrawlingDto crawlingDto = crawlingServerClient.receiveRecentData().getBody();
        keywordService.makeKeyword(crawlingDto);
        return crawlingDto;
    }

    @GetMapping("/recent")
    public List<String> showRecent() {
        return keywordService.showRecentTopKeywords();
    }
}
