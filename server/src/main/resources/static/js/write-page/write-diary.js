const date = document.querySelector(".date")
const writeBtn = document.querySelector(".write-btn")
const today = new Date();

date.innerText = `${today.getFullYear()}.` +
    `${today.getMonth() + 1 < 10 ? "0" + today.getMonth() + 1 : today.getMonth() + 1}.` +
    `${today.getDate() < 10 ? "0" + today.getDate(): today.getDate()}`;

writeBtn.addEventListener("click", writeDiary);

async function writeDiary() {
    const content = document.querySelector(".diary-content").value;
    const mood = document.querySelector(".mood-btn").children[0];
    const moodLocation = mood.src.substring(mood.src.indexOf("/images"));
    const uploadImage = null;

    var formData = new FormData();
    const json = JSON.stringify({
        content: content,
        moodLocation: moodLocation
    });
    formData.append("data", new Blob([json], {type: "application/json"}));
    formData.append("file", uploadImage);

    const location = await fetch("/api/diary", {
        method: "post",
        body: formData
    })
    .then(response => response.headers.get("location"))
    .then(location => location.substring(4));

    showSuccess(location);
}

function showSuccess(location) {
    const background = document.querySelector(".background");

    background.outerHTML = `<div style="background-image: url('/images/write-page/success-background.svg'); background-repeat: no-repeat; background-size: contain; height:100%;">
                                <div style="width: 100%; height: 50px; position: relative; top: 427px;">
                                    <div style="position:absolute; left: 107px;">
                                        <a href=${location} style="width: 150px; height: 50px; flex-shrink: 0; border-radius: 20px; border: 0.5px solid var(--kakao-logo, #000); background: #FFF; float: left; position: relative; text-decoration-line: none;">
                                            <p style="color: var(--kakao-logo, #000); text-align: center; font-size: 16px; font-style: normal; font-weight: 600; line-height: 34px; letter-spacing: -0.4px;">확인</p>
                                        </a>
                                    </div>
                                  </div>
                              </div>`;
}