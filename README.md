# team_fresh_project

## 요구사항
- 로그인 기능
  - **margin, padding, 등 값은 자유 구현**
  - 실제로 버튼 이벤트는 **‘로그인’ 버튼만 기능 구현**
  - 나머지 버튼 클릭 시 **버튼의 제목이 토스트로 출력**
- 무한 스크롤 기능
  - 하단 탭을 전환 시 **게시판 이외에는 빈 화면을 넣기**
  - **게시글의 내용을 보여주는 어댑터를 구현**
  - 무한스크롤 구현은 **20개 단위**로 RecyclerView을 이용

## 구현 계획 (기능: 100%, Unit Test Coverage: 100%)
1. android clean architecture에 따라 모듈 분리[O]  
2. 각 모듈 gradle 세팅[O]  
3. 로그인 구현  
   3-1. 로그인 data layer test code 작성, data layer 구현[O]  
   3-2. 로그인 domain layer test code 작성, domain layer 구현[O]  
   3-3. 로그인 presentation layer viewmodel test code 작성, viewmodel 구현[O]  
   3-4. 로그인 ui 구현[O]
4. 게시판 구현  
   4-1. 게시판 data layer test code 작성, data layer 구현[O]  
   4-2. 게시판 domain layer test code 작성, domain layer 구현[O]  
   4-3. 게시판 presentation layer viewmodel test code 작성, viewmodel 구현[O]  
   4-4. 게시판 ui 구현[O]  

## 🏗 Project Structure
![teamfresh_architecture](https://user-images.githubusercontent.com/48168117/203956629-c58efdd4-3a8b-43d5-b066-df10f8b3eb24.png)

## 📖 Tech Skill
#### Architecture
- MVVM Pattern
- Clean Architecture in Android (presentation, domain, data)
#### UI
- AppCompact
- RecyclerView
- ViewPager2
- Glide
#### Network
- Retrofit2
- Okhttp
#### DI
- Hilt
#### Unit Test
- Junit4
- Truth
- Coroutine Test
- Mockk
- Mock Webserver
#### ETC
- Coroutine
- Gson
- Room
- Navigation Component
- Paging3

## 결과

https://user-images.githubusercontent.com/48168117/203968105-85e8dc68-ebdb-4748-a8d1-73b8be32381e.mp4  

https://user-images.githubusercontent.com/48168117/203968182-300f08a5-157b-4898-bea2-75e596219c01.mp4  

## Developer
[@Sangmeebee](https://github.com/Sangmeebee)
> [11월 21일] 개발 시작  
> [11월 25일] 개발 종료








