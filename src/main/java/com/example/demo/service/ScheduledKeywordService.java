package com.example.demo.service;


import com.example.demo.client.CrawlingServerClient;
import com.example.demo.dto.CrawlingDto;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ScheduledKeywordService {
    private final KeywordService keywordService;
    private final CrawlingServerClient crawlingServerClient;

    // 매일 아침 8시부터 저녁 10시까지 한시간마다 실행
    @Scheduled(cron = "0 0 8-22 * * *")
    public void scheduledKeywordExtraction() {
        LocalDateTime now = LocalDateTime.now();
        if(now.getHour() >= 8 && now.getHour() <=22) {
            crawlingServerClient.crawling();
            CrawlingDto crawlingDto = crawlingServerClient.receiveRecentData().getBody();
            keywordService.makeKeyword(crawlingDto);
        }
    }
}
