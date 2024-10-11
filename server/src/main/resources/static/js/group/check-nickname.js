const nickname = document.querySelector(".name-textarea")
const error = document.querySelector(".error-text")

nickname.addEventListener("input", checkNickname)

function checkNickname() {
    checkFormat();
    console.log(`test value: ${nickname.value}`);
}

function checkFormat() {
    const specialChar = /[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/;

    error.innerText = "";
    console.log(specialChar.test(nickname.value));
    if (specialChar.test(nickname.value)) {
        error.innerText = "특수문자는 사용할 수 없습니다.";
    }
}
