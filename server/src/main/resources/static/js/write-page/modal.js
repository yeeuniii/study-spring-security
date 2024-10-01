const modal = document.querySelector("#modal");
const modalBody = modal.querySelector(".modal-body");
const moodBtn = document.querySelector(".mood-btn");
const photoBtn = document.querySelector(".photo-btn");
const emojiDiv = modalBody.querySelector(".emojies");
const photoDiv = modalBody.querySelector(".photo");

var currentModal = null

modal.style.display = "none";

backgroundImage = new Image();
backgroundImage.addEventListener("load", () => modal.style.backgroundImage = `url('${backgroundImage.src}')`)
backgroundImage.src = "/images/write-page/modal/background.svg";

moodBtn.addEventListener("click", (event) => {
    clickModalBtn(event);
    setTimeout(() => changeDisplay(emojiDiv, photoDiv), 300);
});

photoBtn.addEventListener("click", (event) => {
    clickModalBtn(event);
    setTimeout(() => changeDisplay(photoDiv, emojiDiv), 300);
});

Array.from(emojiDiv.querySelectorAll("a")).forEach(mood => {
    mood.addEventListener("click", changeMood);
});

function clickModalBtn(event) {
    if (modal.style.display === "block") {
        closeModal();

        if (event.target !== currentModal && currentModal != null) {
            setTimeout(() => openModal(), 300);
            currentModal = event.target;
        }
    } else {
        openModal();
        currentModal = event.target;
    }
}

function closeModal() {
    modal.style.transform = "translateY(100%)";
    setTimeout(() => modal.style.display = "none", 300);
}

function openModal() {
    modal.style.display = "block";
    setTimeout(() => modal.style.transform = "translateY(0)", 10);
}

function changeMood(event) {
    const moodBtn = document.querySelector(".mood-btn");
    moodBtn.children[0].src = event.target.src;
    closeModal();
}

function changeDisplay(element1, element2) {
    element1.style.display = "block";
    element2.style.display = "none";
}
