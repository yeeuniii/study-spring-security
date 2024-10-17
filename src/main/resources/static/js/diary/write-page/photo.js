const photoBtn = document.querySelector(".photo-btn");
const photoInput = document.querySelector("#photo-input");

photoBtn.addEventListener("click", (event) => clickInput(event, photoInput));
photoInput.addEventListener("change", uploadImage);

function clickInput(event, input) {
    event.preventDefault();
    input.click();
}

function uploadImage(event) {
    const file = event.target.files[0];

    const reader = new FileReader();

    reader.onloadend = function () {
        photoBtn.classList.remove("photo-btn");
        photoBtn.innerHTML = `
                            <img src="${reader.result}" class="image">
                                <div class="cancel-photo cancel-icon">
                                    <img src="/images/diary/write-page/cancel-upload.svg" class="cancel-icon">
                                </div>
                            `;
        closeModal();
    };

    if (file) {
        reader.readAsDataURL(file);
    }
}
