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

document.getElementById("search-button").addEventListener("click", function(){
    const s_code = document.getElementById("studyCode-input-search").value;
    const s_password = document.getElementById("studyPassword-input-search").value;
    const data ={
        s_code : s_code,
        s_password : s_password
    };

    fetch('searchStudy',{
        method : 'POST',
        header: {
            'Content-Type' : 'application/json',
        },
        body : JSON.stringify(data),
    })
        .then(response => response.json())
        .then(data =>{
            console.log("success");
            console.log(s_code + " s_code 는");
        })
        .catch((error) => {
            console.error('Error:', error);
        });

})

