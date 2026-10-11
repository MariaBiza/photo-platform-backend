const addPhotoButton = document.getElementById("addPhoto");
const catalog = document.getElementById("catalog");

addPhotoButton.addEventListener('click', function() {
    if (!confirm('Add photo?')) return;

    fetch("http://localhost:8080/add", {
        method: "POST"
    })
    .then(response => {
        if (!response.ok) throw new Error(`HTTP ${response.status}`);
        return response.text();
    })
    .then(data => console.log(data))
    .catch(error => console.log("Error: ", error));
});

catalog.addEventListener('click', function() {
//    fetch("http://localhost:8080/all", {
//        method: "GET"
//    })
//    .then(response => {
//        if (!response.ok) throw new Error(`HTTP ${response.status}`);
//        return response.text();
//    })
//    .then(data => console.log(data))
//    .catch(error => console.log("Error: ", error));
    window.location.assign("/catalog");
});