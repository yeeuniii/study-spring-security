let join = false;

const STEP2_HTML_JOIN = `<div class="step-content">
                        <div style="width: 100%; height: 34px;">
                            <span class="subject">그룹코드를 입력해주세요.</span>
                        </div>
                        <div class="input-box">
                            <div class="input-textarea">
                                <label>
                                    <textarea class="group-code input-value" placeholder="그룹코드" spellcheck="false"></textarea>
                                </label>
                            </div>
                        </div>
                    </div>`

const STEP2_HTML_CREATE = `<div class="step-content">
                        <div style="width: 100%; height: 34px;">
                            <span class="subject">스프링의 이름을 지어주세요.</span>
                        </div>
                        <div class="input-box">
                            <div class="input-textarea">
                                <label>
                                    <textarea class="group-name input-value" placeholder="그룹명" spellcheck="false" maxlength="10"></textarea>
                                </label>
                            </div>
                        </div>
                        <div class="error-message" style="width: 283px; height: 34px; float: left; position: relative; left: 46px; top: 24px;">
                            <span class="error-text"></span>
                        </div>
                    </div>`

function getStep2Html() {
    if (join) {
        return STEP2_HTML_JOIN;
    }
    return STEP2_HTML_CREATE;
}

function drawStep2() {
    note_body.innerHTML = getStep2Html();

    initStep2();
}

function initStep2() {
    const confirmBtn = document.querySelector(".confirm-btn");
    inputValue = document.querySelector(".input-value");

    inputValue.addEventListener("input", () => {
        changeCodeBoxColor();
        if (!join) {
            const error = document.querySelector(".error-text");
            error.innerText = verifyGroupName();
        }
    });
    confirmBtn.addEventListener("click", clickConfirmBtn);
}

function changeCodeBoxColor() {
    const inputBox = document.querySelector(".input-box");

    inputBox.style.border = "0.5px solid #767676";
    if (inputValue.value !== "") {
        inputBox.style.border = "2px solid #FC0";
    }
}

function verifyGroupName() {
    const groupName = document.querySelector(".group-name");
    const specialChar = /[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/;
    const whiteSpace = /^\s/;

    if (specialChar.test(groupName.value)) {
        return "특수문자는 사용할 수 없습니다.";
    }
    if (whiteSpace.test(groupName.value)) {
        return "공백으로 시작할 수 없습니다.";
    }
    if (groupName.value.length > 10) {
        return "최대 10자까지 입력 가능합니다.";
    }
    return "";
}

function clickConfirmBtn() {
    if (join) {
        verifyGroupCode();
    }
}

function verifyGroupCode() {
    const groupCode = document.querySelector(".group-code");

    fetch("/api/groups/code/verify", {
        method: "POST",
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            "code": groupCode.value
        })
    }).then(response => {
        if (response.status === 200) {
            return response.json();
        }
    }).then(data => window.localStorage.setItem("groupId", data.groupId));
}
