const nickname = document.querySelector(".name-textarea")
const error = document.querySelector(".error-text")

nickname.addEventListener("input", checkNickname)

function checkNickname() {
    error.innerText = "";

    if (nickname.value !== "" && checkFormat()) {
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
