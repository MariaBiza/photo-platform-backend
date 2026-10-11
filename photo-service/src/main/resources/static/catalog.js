const deletePhotoButton = document.getElementById("deletePhoto");

deletePhotoButton.addEventListener('click', function() {
    const photoId = this.dataset.id;

    if (!confirm('Delete photo?')) return;

    fetch(`http://localhost:8080/photo/${photoId}`, {
        method: "DELETE"
    })
    .then(response => {
        if (!response.ok) throw new Error(`HTTP ${response.status}`);
        return response.text();
    })
    .then(data => console.log(data))
    .catch(error => console.log("Error: ", error));
});