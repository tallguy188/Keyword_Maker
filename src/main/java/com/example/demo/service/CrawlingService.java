package com.example.demo.service;

import com.example.demo.dto.CrawlingDto;
import com.example.demo.entity.Crawling;
import com.example.demo.repository.CrawlingRepository;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CrawlingService {

    private final CrawlingRepository crawlingRepository;

    private final int WINDOW_SIZE = 10;

    List<String> pressIdList = List.of("081", "055", "018", "057", "032", "368", "028", "015", "029", "025",
                                                "016", "308", "056", "047", "277", "109", "422", "117", "052", "076",
                                                "214", "139", "314", "215", "366", "003", "030", "038", "044", "020",
                                                "006", "022", "293", "031", "014", "023", "008", "011", "327", "330",
                                                "326", "005", "241", "092", "073", "904", "002", "009", "021", "930",
                                                "079");


    public void crawl(){

        StringBuilder textData = new StringBuilder();
        // 각각 언론사 별로 num 언론사 추가
        for (String id: pressIdList) {
            String url = "https://newsstand.naver.com/include/page/" + id + ".html";

            try {
                // url에서 HTML 가져옴
                Document document= Jsoup.connect(url).get();
                // 텍스트 추출
                String text = document.text();

                // 스트링빌더에 추가
                textData.append(text + "\n");
            }catch (IOException e) {
                e.printStackTrace();

            }
        }
        // 저장
        Crawling crawling = Crawling.builder()
                .rawData(textData.toString())
                .createdTime(LocalDateTime.now())
                .build();
        crawlingRepository.save(crawling);


        // 윈도우 사이즈에 맞춰 가장 오래된 데이터 삭제
        long dataCount = crawlingRepository.count();

        if(dataCount > WINDOW_SIZE) {
            List<Crawling> allData = crawlingRepository.findAllByOrderByCreatedTimeAsc();
            Crawling oldData = allData.get(0);
            crawlingRepository.delete(oldData);
        }
    }



    public Crawling findRecentData() {
        Crawling crawling = crawlingRepository.findFirstByOrderByCreatedTimeDesc();
        return crawling;

    }
}