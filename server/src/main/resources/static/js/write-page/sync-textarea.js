const DEFAULT_HEIGHT = 50;
const textarea = document.querySelector(".diary-content");
const textBtn = document.querySelector(".text-btn");

textarea.addEventListener("input", syncHeight);
textBtn.addEventListener("click", clickTextBtn);

function syncHeight(event) {
    const target = event.target;

    target.style.height = `${DEFAULT_HEIGHT}px`;
    target.style.height = `${target.scrollHeight - 4}px`;
}

function clickTextBtn() {
    closeModal();
    textarea.focus();
}
