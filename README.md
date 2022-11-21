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

## 구현 계획
1. android clean architecture에 따라 모듈 분리[]
2. 각 모듈 gradle 세팅[]
3. 로그인 구현  
   3-1. 로그인 data layer test code 작성, data layer 구현[]  
   3-2. 로그인 domain layer test code 작성, domain layer 구현[]  
   3-3. 로그인 presentation layer viewmodel test code 작성, viewmodel 구현[]  
   3-4. 로그인 ui 구현[]
4. 게시판 구현  
   4-1. 게시판 data layer test code 작성, data layer 구현[]  
   4-2. 게시판 domain layer test code 작성, domain layer 구현[]  
   4-3. 게시판 presentation layer viewmodel test code 작성, viewmodel 구현[]  
   4-4. 게시판 ui 구현[]

## 🏗 Project Structure
![teamfresh_architecture](https://user-images.githubusercontent.com/48168117/203066088-f818786e-a702-41ae-9689-0a6b12d0c05d.png)

## 📖 Tech Skill
#### Architecture
- MVVM Pattern
- Clean Architecture in Android (presentation, domain, data)
#### UI
- AppCompact
- RecyclerView
- Glide
#### Network
- Retrofit
- Okhttp
#### DI
- Hilt
#### ETC
- Coroutine
- Gson
- Room
- Navigation Component
- Paging

## Developer
[@Sangmeebee](https://github.com/Sangmeebee)
> [11월 21일] 개발 시작
