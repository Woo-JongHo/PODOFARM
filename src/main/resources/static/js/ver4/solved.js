document.addEventListener('DOMContentLoaded', () => {
    const readModeDiv = document.getElementById('read-mode');
    const editModeDiv = document.getElementById('edit-mode');
    const editButton = document.getElementById('edit-button');
    const cancelButton = document.getElementById('cancel-button');
    const sourceEdit = document.getElementById('source-edit');
    const sourceContent = document.querySelector('.markdown-body');

    const problemId = document.currentScript.getAttribute('data-problem-id');
    console.log(problemId);
    // 이제 problemId 변수를 여기서 사용할 수 있습니다


    console.log("problemId 가져왔어? " + problemId);

    function enterEditMode() {
        sourceEdit.value = sourceContent.innerText; // 마크다운을 일반 텍스트로 설정
        readModeDiv.style.display = 'none';
        editModeDiv.style.display = 'block';
        window.location.href = '/ps/edit/' + problemId;
    }

    function exitEditMode() {
        readModeDiv.style.display = 'block';
        editModeDiv.style.display = 'none';
        window.location.href = '/ps/view/' + problemId;
    }

    editButton.addEventListener('click', enterEditMode);
    cancelButton.addEventListener('click', exitEditMode);

    // URL을 통해 바로 수정 모드로 진입
    if (location.pathname.includes('/ps/edit/')) {
        enterEditMode();
    }

    window.addEventListener('popstate', (event) => {
        if (location.pathname.includes('/ps/edit/')) {
            enterEditMode();
        } else {
            exitEditMode();
        }
    });
});