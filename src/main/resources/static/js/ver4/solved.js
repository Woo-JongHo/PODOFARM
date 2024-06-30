document.addEventListener('DOMContentLoaded', function () {
    const editButton = document.getElementById('edit-button');
    const saveButton = document.getElementById('save-button');
    const cancelButton = document.getElementById('cancel-button');
    const readMode = document.getElementById('read-mode');
    const editMode = document.getElementById('edit-mode');
    const editContent = document.getElementById('edit-content');
    const markdownBody = document.querySelector('.markdown-body');

    console.log("test1");

    editButton.addEventListener('click', function () {
        window.location.href = '/ps/edit/120810';  // 원하는 URL로 변경
    });


});