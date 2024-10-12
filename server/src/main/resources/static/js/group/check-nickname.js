const nicknameBox = document.querySelector(".name-box")
const nickname = document.querySelector(".name")
const error = document.querySelector(".error-text")

nickname.addEventListener("input", checkNickname)

function checkNickname() {
    error.innerText = "";
    nicknameBox.style.border = "0.5px solid #767676";

    if (nickname.value !== "" && checkFormat()) {
        nicknameBox.style.border = "2px solid #FC0";
        checkDuplicate();
    }
}

function checkFormat() {
    const specialChar = /[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"₩]/;

    if (specialChar.test(nickname.value)) {
        error.innerText = "특수문자는 사용할 수 없습니다.";
        return false;
    }
    return true;
}

function checkDuplicate() {
    const groupId = window.localStorage.getItem("groupId");

    fetch(`/api/groups/${groupId}/nickname/verify?nickname=${nickname.value}`)
        .then(response => {
            if (response.status !== 200) {
                error.innerText = "이미 존재하는 이름입니다.";
            }
        })
}
