const date = document.querySelector(".date")
const writeBtn = document.querySelector(".write-btn")
const today = new Date();

date.innerText = `${today.getFullYear()}.${today.getMonth() + 1}.${today.getDate()}`;

console.log(writeBtn);
writeBtn.addEventListener("click", writeDiary);

function writeDiary() {
    console.log(date);
    const content = document.querySelector(".diary-content").value;
    const mood = document.querySelector(".mood-btn").children[0];
    const moodLocation = mood.src.substring(mood.src.indexOf("/images"));
    const uploadImage = null;

    var formData = new FormData();

    const json =  JSON.stringify({
        content: content,
        moodLocation: moodLocation
    });
    formData.append("data", new Blob([json], {type: "application/json"}));
    formData.append("file", uploadImage);

    fetch("/api/diary", {
        method: "post",
        body: formData
    })
        .then(response => response.headers.get("Content-location"))
        .then(location => window.location.href = location);
}
