# PODOFARM
---

## 프로그래머스를 이용한 국비교육생 Java Study Flatfrom



### extension 분석
----------------------

익스텐션이 사용자 정보를 요청하는 것이 아니라 애초에 백준허브를 사용할 때 레파지토리를 등록하는 로직이였음

따라서, 인증과 인가가 필요하지 않다


1. 익스텐션을 만들고, 이메일을 입력하게 만들어서
문제를 풀면 이메일주소와 함께 서버로 넘긴다 
2. 이렇게 되면 누구든 이메일만으로 데이터를 넣을 수 있어서 문제가 생긴다
3. 이메일주소와 스터디 코드를 입력하게 만든다.
4. 이것은 포도팜에서 구글로그인을 먼저 해야하므로, 스터디 코드가 없는 사람에겐 
포도팜 로그인을 먼저 권장하는 UI를 만든다

이메일 주소를 입력받게 하려했으나 

이미 디비의 PK를 고유 ID로 짰기 때문에,
고유 ID 를 입력하는 란을 만들기로


입력란을 하나 추가할 것이고
그 다음 작업은 데이터를 확인하고 넘기는 방법에 로컬 스토리지를 어떻게 사용하고 이용할 것인가를 
확인,


1. 익스텐션 화면에서 연동하기에 
 - 스터디코드
 - 이메일주소 쓰기 구현


   
<span style="color:yellow">  -> 작업완료 받는 것은 json, fetch API로 이용하였고 리턴되는 데이터는
ResponseEntity 이용,  ResponseEntity는 HttpEntity의 상속클래스, 데이터를 넘길때 사용
</span>


2. 연동버튼시 로컬스토리지에 두 개를 키 값으로 저장


3. 문제를 풀 때에는 로컬스토리지가 생성되어있는지 확인하는 checkEnable을 구현
 - check enable에서는 로컬스토리지에 키 값이 입력되어 있는지를 먼저 확인하고
 - 있으면 진행 없으면, 만들기
 - 연동하기버튼 및 취소 버튼을 통해 remove도 구현할 것

## 작업목록
--------------------
1. 로더가 문제를 맞춰도 계속 돌아감 -> 익스텐션에서 스탑로더 구현
2. 현재 제출일자가 찍히지 않는 문제 발생, readme에 아예 란이 없음 확인 필요 (이미 맞춘 문제라 안나오는 걸 수 있음)
3. CODE 테이블에서 제출일자시간만 따로 빼놓은 자료 하나 만들 것. -> 잔디심기를 위해
4. CODE 테이블에 데이터가 다 된다면 이제, 잔디심기 구현 및 CSS 작업 다시 시작할 것 


figma. 디자인 작업 중 개인공간 장소 작업 중  : 컴포넌트 작업 
DASHBOARD
![image](https://github.com/Woo-JongHo/Algorithm/assets/117367145/96edc9a7-f6b3-42e1-9998-01175a3962aa)

PERSONALPAGE
![image](https://github.com/Woo-JongHo/PODOFARM/assets/117367145/f2d709f5-d78a-4766-9f16-ec4f1b25afbb)



## 완료
-----------------
1.익스텐션창에 고유번호, 스터디코드 입력란 생성
2. 서버에서 익스텐션 내용 확인 후, 확인 OR 실패 설정
3. 확인 시, 익스텐션에서 고유번호 정보 저장 (수정 기능 추후)
4. 현재 익스텐션 내용에서 파싱 데이터를 서버로 전달하는 작업



## 작업 내용
1. CSP 보안정책에 의해 팝업창에서 데이터를 저장하고 이동시키는건 되지 않음
-> welcome.html로 이동하여 그곳에서 고유번호, 스터디 코드 입력란을 받을 예정
-> CSP 는 127, LOCALHOST를 외부로 간주하기 때문에 html에서 js 동작하는 것을 막고있음
따라서 리스너만 js에 넣으면 문제없음, welcome에서해도되고 popup에서해도됨
2. 


