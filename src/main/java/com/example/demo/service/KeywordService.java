package com.example.demo.service;

import com.example.demo.dto.CrawlingDto;
import com.example.demo.entity.Keyword;

import com.example.demo.repository.KeywordRepository;
import lombok.RequiredArgsConstructor;

import org.openkoreantext.processor.OpenKoreanTextProcessor;
import org.openkoreantext.processor.OpenKoreanTextProcessorJava;
import org.springframework.stereotype.Service;
import org.openkoreantext.processor.KoreanTokenJava;
import org.openkoreantext.processor.OpenKoreanTextProcessorJava;
import org.openkoreantext.processor.tokenizer.KoreanTokenizer;


import scala.collection.Seq;

import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class KeywordService {
    private final KeywordRepository keywordRepository;

    public void makeKeyword(CrawlingDto crawlingDto) {

        String rawData = crawlingDto.getRawData();
        long rawDataId = crawlingDto.getRawDataId();

        //rawData 어구추출
        CharSequence normalized = OpenKoreanTextProcessorJava.normalize(rawData);

        // 추출된 어구 토큰화
        Seq<KoreanTokenizer.KoreanToken> tokens = OpenKoreanTextProcessorJava.tokenize(normalized);

        // 명사 추출 + 빈도 계산
        List<String> nouns = new ArrayList<>();
        scala.collection.Iterator<KoreanTokenizer.KoreanToken> iterater = tokens.iterator();
        while(iterater.hasNext()) {
            KoreanTokenizer.KoreanToken token = iterater.next();
            if(token.pos().toString().equals("Noun")) {
                nouns.add(token.text());
            }
        }

        // 의미 없는 단어와 한 글자 단어를 제외하는 필터링
        Set<String> meaninglessWords = new HashSet<>(Arrays.asList("뉴스", "확성기", "뉴스스탠드", "정부", "한국", "오늘", "단독", "공개", "방송", "대응", "명령",
                "논란", "재개", "영역", "집단", "신고", "사건", "경제", "서울", "최고", "중앙",
                "이번", "추가", "사망", "세계", "상임", "이유", "검토", "위반", "경찰", "효과",
                "우리", "가능성", "투자","올해","불법","영상","이상","국민", "대표", "선출", "의혹",
                "구성", "전환", "글로벌", "고소", "검찰", "사실", "현장", "혐의", "속보", "연장", "내년",
                "언론","판결","입장","발표","직접"        ));
        Pattern hangulPattern = Pattern.compile("^[가-힣]+$");

        List<String> filteredNouns = nouns.stream()
                .filter(word -> (word.length() > 1 || !hangulPattern.matcher(word).matches()) && !meaninglessWords.contains(word))
                .collect(Collectors.toList());


        // 토큰 빈도 계산
        Map<String,Integer> frequencyMap = new HashMap<>();
        for(String noun: filteredNouns) {
            frequencyMap.put(noun,frequencyMap.getOrDefault(noun,0) +1);
        }

        // 빈도수 기준 정렬 후 상위 20개 추출
        List<String> topKeywords = frequencyMap.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(20)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());



        System.out.println("빈출 키워드 20개 : ");
        topKeywords.forEach(System.out::println);



        create(rawDataId, topKeywords);

    }

    public void create(long rawDataId, List<String> topKeywords) {

        Keyword keyword = Keyword.builder()
                .rawDataId(rawDataId)
                .keywordList(topKeywords)
                .createdTime(LocalDateTime.now()).build();
        keywordRepository.save(keyword);

    }

    public List<String> showRecentTopKeywords() {
        Keyword keyword = keywordRepository.findFirstByOrderByCreatedTimeDesc();
        List<String> recentTopKeywords = keyword.getKeywordList();
        List<String>rankedList = rank(recentTopKeywords);
        return rankedList;
    }

    public List<String> rank(List<String> keywordList) {
        List<String> rankedList = new ArrayList<>();
        for (int i = 0; i < keywordList.size(); i++) {
            // 인덱스를 포함한 문자열을 생성합니다.
            rankedList.add((i + 1) + ". " + keywordList.get(i));
        }
        return rankedList;
    }
}
