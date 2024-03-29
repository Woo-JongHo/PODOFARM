# PODOFARM
---

## 프로그래머스를 이용한 국비교육생 Java Study Flatfrom



### extension 분석
----------------------
1. programmers.js
- 프로그래머스 주소 확인, 로더 실행
- '정답'이라는 글자가 보이면 파싱시작
- parsing.js로 넘어감
2. parsing.js
- 문제 데이터를 가지고옴
3. programmesr.js
- startUpload를 통해 util.js에 접근
4. util.js 는 아이콘 및 ui에 관련된 정보로 현재는 구현되지 않음
5. hook과 token을 받을 준비

오류가 title을 못받아오는것이였는데
textContent가 null이였음.

const title = document.querySelector('.nav-item.algorithm-nav-link.algorithm-title.active .challenge-title').textContent.trim();
로 변경, c

## SQL

### STUDY 
``` 
    SQL문 종류
    1. 스터디 생성
    2. 스터디 방장 권한 부여
    3. 유저의 스터디 유무 확인 (접속 시)
    4. 스터디 코드 불러오기
    5. 스터디 명 불러오기
    6. 스터디 총 인원 불러오기
    7. 스터디 남은 일 수 불러오기

