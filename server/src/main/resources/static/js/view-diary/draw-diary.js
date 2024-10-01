async function drawDiary() {
    const moodBtn = document.querySelector(".mood-btn");
    const date = document.querySelector(".date");
    const content = document.querySelector(".diary-content");

    const diary = await fetch(`/api/diary/${window.diaryId}`, {
        method: "get"
    })
        .then(response => response.json());

    console.log(diary);

    date.innerText = diary.createdAt;
    moodBtn.children[0].src = diary.moodLocation;
    content.value = diary.content;

    if (diary.imageApiPath) {
        drawUploadImage(diary.imageApiPath);
    }
}

async function drawUploadImage(imageApiPath) {
    const image = document.querySelector(".image");
    const response = await fetch(imageApiPath)
        .then(response => {
            if (!response.ok) {
                // TODO: 이미지 조회 실패 시 예외처리
            }
            return response.blob();
        })
    image.className = "image";
    image.children[0].src = URL.createObjectURL(response);
}

drawDiary();
