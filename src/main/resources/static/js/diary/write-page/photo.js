const cameraBtn = document.querySelector(".modal-btn.camera");
const cameraInput = document.querySelector("#camera-input");
const image = document.querySelector(".photo-btn");

cameraBtn.addEventListener("click", (event) => {
    event.preventDefault();
    cameraInput.click();
})

cameraInput.addEventListener("change", uploadImage);


function uploadImage(event) {
    const file = event.target.files[0];
    if (file) {
        image.classList.remove("photo-btn");
        image.children[0].src = URL.createObjectURL(file);
        image.children[0].classList = ["image"];
        closeModal();
    }
}