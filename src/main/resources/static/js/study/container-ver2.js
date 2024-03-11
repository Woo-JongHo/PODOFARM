document.getElementById("createStudy-button").addEventListener("click", function(){

    const s_name = document.getElementById("studyName-input").value;
    const s_password = document.getElementById("studyPassword-input").value;
    const s_start = document.getElementById("studyStart-input").value;
    const s_end = document.getElementById("studyEnd-input").value;
    const data = {
        s_name : s_name,
        s_password : s_password,
        s_start : s_start,
        s_end : s_end
    };

    fetch('/createStudy', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
    })
        .then(response => response.json())
        .then(data => {
            console.log('스터디 생성이 완료되었습니다:', data);
            console.log("스터디명" + s_name);
            console.log("스터디비밀번호" + s_password);
            console.log("스터디시작일" + s_start);
            console.log("스터디종료일" + s_end);

        })
        .catch((error) => {
            console.error('Error:', error);
        });

})

