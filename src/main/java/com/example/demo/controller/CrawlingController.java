package com.example.demo.controller;

import com.example.demo.entity.Crawling;
import com.example.demo.service.CrawlingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;



@RestController
@RequestMapping("/crawl/")
@RequiredArgsConstructor
public class CrawlingController {

    private final CrawlingService crawlingService;

    @GetMapping("")
    public void crawling() throws IOException {

        crawlingService.crawl();
        crawlingService.findAll();

    }

//    @GetMapping("/all")
//    public List<Crawling> crawlingList {
//        return crawlingService.findAll();
//    }

}
