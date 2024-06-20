document.addEventListener('DOMContentLoaded', function() {
    // DOM이 준비되면 실행될 함수
    var monthLinks = document.querySelectorAll('.month-link');

    monthLinks.forEach(function(link) {
        link.addEventListener('click', function(e) {
            e.preventDefault(); // 기본 동작(페이지 새로고침) 방지

            var selectedMonth = this.getAttribute('data-month'); // 선택된 월 가져오기
            var s_code = '423XDF'/* 여기에서 s_code 값을 가져오는 방식 */;
            
            updateData(s_code, selectedMonth); // 선택된 월을 서버에 전달하고 데이터 업데이트 함수 호출
        });
    });

    function updateData(s_code, selectedMonth) {
        fetch('/' + s_code + '?month=' + encodeURIComponent(selectedMonth))
            .then(function(response) {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.text();
            })
            .then(function(data) {
            })
            .catch(function(error) {
                console.error('Fetch error:', error);
            });
    }
});
