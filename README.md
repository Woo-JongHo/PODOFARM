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