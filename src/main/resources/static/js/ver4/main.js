document.addEventListener("DOMContentLoaded", function() {
    document.querySelectorAll('.month-link').forEach(function(link) {
        link.addEventListener('click', function(event) {
            event.preventDefault();
            const s_code = link.getAttribute('data-s_code');
            const month = link.getAttribute('data-month');

            const data = { s_code: s_code, month: month };

            fetch("/overview", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(data)
            })
                .then(response => response.json())
                .then(result => {
                    console.log("Study members by month:", result);
                    // 서버 응답을 처리하는 로직을 여기에 작성합니다.
                })
                .catch(error => {
                    console.error("Error fetching study members by month:", error);
                });
        });
    });
});