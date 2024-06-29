document.addEventListener('DOMContentLoaded', () => {
    const readModeDiv = document.getElementById('read-mode');
    const editModeDiv = document.getElementById('edit-mode');
    const editButton = document.getElementById('edit-button');
    const saveButton = document.getElementById('save-button');
    const cancelButton = document.getElementById('cancel-button');
    const sourceEdit = document.getElementById('source-edit');
    const sourceContent = document.querySelector('.markdown-body');

    function enterEditMode() {
        sourceEdit.value = sourceContent.innerText;  // 마크다운을 일반 텍스트로 설정
        readModeDiv.style.display = 'none';
        editModeDiv.style.display = 'block';
        window.location.href = '/ps/edit/120810';
    }

    function exitEditMode() {
        readModeDiv.style.display = 'block';
        editModeDiv.style.display = 'none';
        history.pushState(null, '', location.pathname);  // URL 원래대로
    }

    function saveChanges() {
        sourceContent.innerText = sourceEdit.value;  // 마크다운으로 다시 설정하려면 변환 로직 필요
        exitEditMode();
    }

    editButton.addEventListener('click', enterEditMode);
    cancelButton.addEventListener('click', exitEditMode);
    saveButton.addEventListener('click', saveChanges);

    // URL을 통해 바로 수정 모드로 진입
    if (location.search.includes('edit')) {
        enterEditMode();
    }

    window.addEventListener('popstate', (event) => {
        if (location.search.includes('edit')) {
            enterEditMode();
        } else {
            exitEditMode();
        }
    });
});