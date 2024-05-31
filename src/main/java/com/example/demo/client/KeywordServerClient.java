package com.example.demo.client;


import com.example.demo.dto.CrawlingDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "keyword-service", path = "${keyword.server.prefix}")
public interface KeywordServerClient {
    @PostMapping("/keyword")
    ResponseEntity<Void> sendRawData(@RequestBody CrawlingDto crawlingDto);
}
