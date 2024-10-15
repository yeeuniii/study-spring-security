const cameraBtn = document.querySelector(".modal-btn.camera");
const cameraInput = document.querySelector("#camera-input");
const galleryBtn = document.querySelector(".modal-btn.gallery");
const galleryInput = document.querySelector("#gallery-input");
const image = document.querySelector(".photo-btn");

cameraBtn.addEventListener("click", (event) => clickInput(event, cameraInput));
galleryBtn.addEventListener("click", (event) => clickInput(event, galleryInput));

cameraInput.addEventListener("change", uploadImage);
galleryInput.addEventListener("change", uploadImage);

function clickInput(event, input) {
    event.preventDefault();
    input.click();
}

function uploadImage(event) {
    const file = event.target.files[0];

    const reader = new FileReader();

    reader.onloadend = function () {
        image.classList.remove("photo-btn");
        image.children[0].src = reader.result;
        image.children[0].classList = ["image"];
        closeModal();
    };

    if (file) {
        reader.readAsDataURL(file);
    }
}
