
//01 구글로그인 기능
function handleCredentialResponse(response) {
    // decodeJwtResponse() is a custom function defined by you
    // to decode the credential response.
    const responsePayload = decodeJwtResponse(response.credential);

    console.log('js실행 구글로그인기능 구현')
    console.log("ID: " + responsePayload.sub);
    console.log('Full Name: ' + responsePayload.name);
    console.log("Email: " + responsePayload.email);


    //데이터 넘기기

    //JSON DATA를 자바스크립트 객체로 정의합니다
    //FETCH를 통해 엔드포인트로 POST요청을 보냅니다
    //헤더를 요청하여 보낼 데이터의 형식을 말해줍니다
    //앞선 DATA 객체를 JSON으로 변경합니다. 그래야 넘길 수 있습니다.
    const data = {
        id: responsePayload.sub,
        email: responsePayload.email,
        name: responsePayload.name
    }

    console.log("data가 객체가 되었는가?"+data);

    fetch('/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)

    })
        .then(response => response.text()) // response.json() 대신 response.text()를 사용합니다.
        .then(result => {
            console.log('Success:', result);
            // 요청이 성공하면 페이지를 새로고침합니다.
            window.location.reload(true);
        })
        .catch(error => console.error('Error:', error));

//02 구글로그인 토큰 변환 작업
    function decodeJwtResponse(token) {
        let base64Url = token.split('.')[1]
        let base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
        let jsonPayload = decodeURIComponent(atob(base64).split('').map(function(c) {
            return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
        }).join(''));
        return JSON.parse(jsonPayload)
    }


}


