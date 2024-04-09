document.getElementById("connectButton2").addEventListener("click", function() {
    // 입력된 코드 가져오기
    const code = document.getElementById('uniqueIdInput').value;
    const code2 = document.getElementById('studyCodeInput').value;

    console.log('Code saved:', code);
    console.log('Code saved:', code2);

    // 여기서 원하는 동작 수행

    // 새 창 열지 않음
    // window.open('http://your-redirect-url.com', '_blank');
});
