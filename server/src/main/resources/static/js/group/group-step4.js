let join = true;

const STEP4_HTML = `<div class="step-content">
                        <div style="width: 100px; height: 100px;">
                            <img class="selected-pring" src="" style="width: 100px; height: 100px;">
                        </div>
                        <div style="width: 100%; height: 34px; position: relative; top: 16px;">
                            <span class="subject">캐릭터 이름을 지어주세요.</span>
                        </div>
                        <div class="input-box">
                            <div class="input-textarea">
                                <label>
                                    <textarea class="nickname input-value" placeholder="이름" maxlength="10" spellcheck="false"></textarea>
                                </label>
                            </div>
                        </div>
                        <div class="error-message" style="width: 283px; height: 34px; float: left; position: relative; left: 46px; top: 24px;">
                            <span class="error-text"></span>
                        </div>
                    </div>`;

function drawStep4() {
    note_body.innerHTML = STEP4_HTML;

    initStep4();
}

function initStep4() {
    nicknameBox = document.querySelector(".input-box");
    nickname = document.querySelector(".nickname");
    const confirmBtn = document.querySelector(".confirm-btn");

    nickname.addEventListener("input", async () => {
        const error = document.querySelector(".error-text");

        if (nickname.value === "") {
            nicknameBox.style.border = "0.5px solid #767676";

        } else {
            nicknameBox.style.border = "2px solid #FC0";
            error.innerText = await verifyNickname();
        }
    });
    confirmBtn.addEventListener("click", createOrJoinGroup);
}

async function verifyNickname() {
    const specialChar = /[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"₩]/;

    if (specialChar.test(nickname.value)) {
        return "특수문자는 사용할 수 없습니다.";
    }
    if (await isDuplicateNickname()) {
        return "이미 존재하는 이름입니다.";
    }
    return "";
}

async function isDuplicateNickname() {
    const groupId = window.localStorage.getItem("groupId");
    return await fetch(`/api/groups/${groupId}/nickname/verify?nickname=${nickname.value}`)
        .then(response => response.status !== 200);
}

function createOrJoinGroup() {
    if (join) {
        joinGroup();
    } else {
        createGroup();
    }
}

function createGroup() {
    fetch("/api/groups",{
        method: "POST",
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            "groupName": groupName,
            "profileLocation": profileLocation,
            "nickname": nickname.value
        })
    }).then(response => response.json())
        .then(data => data.code)
}

function joinGroup() {
    fetch(`/api/groups/${groupId}/join`,{
        method: "POST",
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            "profileLocation": profileLocation,
            "nickname": nickname.value
        })
    })
}
