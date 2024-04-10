/*
1. 연동하기 버튼 후 반응에 대해서 해야함
 - 연동되었습니다 메세지?
 - 실패하였습니다 코드와 아이디를 확인해주세요

*/ 


document.getElementById("connectButton").addEventListener("click", function() {
    // 입력된 코드 가져오기
    const id = document.getElementById('idInput').value;
    const studyCode = document.getElementById('studyCodeInput').value;

    // 서버로 데이터 전송
    fetch('http://localhost:8080/receive-sync', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ id: id, studyCode: studyCode })
    })
    .then(response => {
        // 서버로부터 받은 응답 처리
        return response.text();
    })
    .then(data => {
        // 받은 데이터에 따라 동작 수행
        if (data === "success") {
            console.log("Success");
            
            //키 값을 무엇으로 해야하는가? 
            
            const problemId = [];
            const dataStorage = {
                id : id,
                studyCode : studyCode,
                problemId  : problemId
            }
          
            chrome.storage.local.set(dataStorage, function() {
                console.log('Data stored:', dataStorage);
            });
        
            
        } else {
            console.log("Fail");
            // 실패 처리
        }
    })
    .catch(error => {
        console.error('Error:', error);
        // 오류 처리
    });
});


