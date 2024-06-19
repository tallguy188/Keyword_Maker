package com.example.demo.client;

import com.example.demo.dto.CrawlingDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "crawling-server")
public interface CrawlingServerClient {

    @GetMapping("/sendRecentData")
    ResponseEntity<CrawlingDto> receiveRecentData();

    @GetMapping("/crawling")
    ResponseEntity<Void> crawling();
}
