document.addEventListener("DOMContentLoaded", function () {
    const bookmarkButton = document.getElementById("bookmark-button");

    bookmarkButton.addEventListener("click", function () {
        // 클릭 시 버튼 스타일 변경
        this.classList.toggle("clicked");

        // JSON 데이터 생성
        const data = {
            filename: document.querySelector(".solvedpage-title-left-filename").innerText,
            date: new Date().toISOString(),
            liked: this.classList.contains("clicked")
        };

        // JSON 데이터를 서버로 전송
        fetch("/updateBookmark", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(data)
        })
            .then(response => response.json())
            .then(result => {
                console.log("Bookmark update result:", result);
            })
            .catch(error => {
                console.error("Error updating bookmark:", error);
            });
    });
});