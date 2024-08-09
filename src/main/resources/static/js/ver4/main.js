document.addEventListener('DOMContentLoaded', function() {
    const monthLinks = document.querySelectorAll('.month-link');
    monthLinks.forEach(link => {
        link.addEventListener('click', function(event) {
            event.preventDefault();
            const selectMonth = this.id;
            updateSelectMonth(selectMonth);
        });
    });
});

function updateSelectMonth(selectMonth) {
    fetch(`/updateMonth`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ selectMonth: selectMonth })
    })
        .then(response => response.text())
        .then(data => {
            document.getElementById('content').innerHTML = data;
        })
        .catch(error => console.error('Error:', error));


}

