init();

function init() {
    drawTodayDate();

    addEventToTextArea();
    addEventToWriteBtn();
}

function addEventToTextArea() {
    const textArea = document.querySelector("textarea");

    textArea.addEventListener("click", closeModal);
}

function addEventToWriteBtn() {
    const writeBtn = document.querySelector(".write-btn")

    writeBtn.addEventListener("click", writeDiary);
}

function drawTodayDate() {
    const date = document.querySelector(".date")
    const today = new Date();

    date.innerText = `${today.getFullYear()}.` +
        `${today.getMonth() + 1 < 10 ? "0" + today.getMonth() + 1 : today.getMonth() + 1}.` +
        `${today.getDate() < 10 ? "0" + today.getDate(): today.getDate()}`;
}

function writeDiary() {
    var formData = new FormData();
    const json = JSON.stringify({
        content: document.querySelector(".diary-content").value,
        moodLocation: getMoodLocation()
    });

    formData.append("data", new Blob([json], {type: "application/json"}));
    formData.append("file", getUploadImage());

    fetch("/api/diary", {
        method: "post",
        body: formData
    })
        .then(response => {
            if (response.status !== 201) {
                throw new Error();
            }
            return response.headers.get("content-location");
        })
        .then(contentLocation => {
            closeModal(); // TODO: 약간의 딜레이 문제
            showSuccess(contentLocation);
        })
        .catch(() => {
            // ToDo: 예외 처리 로직 추가
        })
}

function getMoodLocation() {
    const moodIconLocation = "/images/write-page/mood_icon.svg";
    const mood = document.querySelector(".mood-btn").children[0];
    const moodLocation = mood.src.substring(mood.src.indexOf("/images"));

    if (moodLocation === moodIconLocation) {
        return null;
    }
    return moodLocation;
}

function getUploadImage() {
    const uploadImage = null;

    // TODO: 업로드 이미지 설정

    return uploadImage;
}

function showSuccess(location) {
    const background = document.querySelector(".background");

    background.outerHTML = `<div style="background-image: url('/images/write-page/success_background.svg'); background-repeat: no-repeat; background-size: contain; height:100%;">
                                <div style="width: 100%; height: 50px; position: relative; top: 427px;">
                                    <div style="position:absolute; left: 107px;">
                                        <a href=${location} style="width: 150px; height: 50px; flex-shrink: 0; border-radius: 20px; border: 0.5px solid var(--kakao-logo, #000); background: #FFF; float: left; position: relative; text-decoration-line: none;">
                                            <p class="check-btn">확인</p>
                                        </a>
                                    </div>
                                  </div>
                              </div>`;
}
