# Keyword Maker

"뉴스 타이틀 크롤링 키워드 추출 서비스"라는 주제를 잡고 진행한 개인프로젝트입니다. MSA(MicroService Architecture) 기반으로 만들어진 프로젝트입니다. 

## 👨‍🏫 프로젝트 소개 

네이버 뉴스스탠드에서 각 언론사별 번호를 리스트에 추가한 후, Jsoup을 사용하여 크롤링을 진행했습니다. 그 다음 크롤링한 데이터를 바탕으로 OKT(Open Korean Text)를 사용하여 빈출 키워드 20개를 추출했습니다.

## ⚒️ 기술 스택
- Java, Spring Boot, Spring Cloud Gateway, Spring Cloud Netflix Eureka, Spring Cloud OpenFeign, Jsoup, OKT (Open Korean Text)

## 📝 프로젝트 순서도
![프로젝트 순서도](https://github.com/tallguy188/Keyword_Maker/assets/80328668/46b3be85-5948-46f4-9ae7-7020a5ad5864)

## 📌 주요 작업
- MSA 기반 서비스 설계 및 구축
  - Gateway Server : API Gateway, 라우팅 및 로드 밸런싱
  - Discovery Server : 서비스 등록 및 검색
  - Crawling Server : Jsoup을 사용한 네이버 뉴스 타이틀 크롤링
  - Keyword Server : FeignClient를 통한 데이터 수신 및 OKT를 사용한 키워드 추출
 
## 🔗 링크

프로젝트에 대한 상세 내용이 궁금하시면, 아래 블로그에 자세히 정리해 두었으니 방문해 주세요.

- 정리 블로그 : https://velog.io/@tallguy188/posts
