const STEP1_HTML = `
                    <div style="width: 195px; height: 34px;">
                        <span class="subject">스프링에 참여해요!</span>
                    </div>
                    <div class="group">
                        <div class="join-btn">
                            <a href="#" class="join circle">
                                <div style="width: 64px; height: 90px;">
                                    <img class="group-icon" src="/images/group/group_join.svg">
                                    <img class="group-icon select" src="/images/group/group_join_white.svg">
                                    <span class="text group-text">그룹 가입</span>
                                </div>
                            </a>
                        </div>
                        <div class="create-btn">
                            <a href="#" class="create circle">
                                <div style="width: 64px; height: 90px;">
                                    <img class="group-icon" src="/images/group/group_create.svg">
                                    <img class="group-icon select" src="/images/group/group_create_white.svg">
                                    <span class="text group-text">그룹 생성</span>
                                </div>
                            </a>
                        </div>
                    </div>
                    <div class="question">
                        <span class="text question-text"></span>
                    </div>`

function drawStep1(direction) {
    const step_content = document.createElement("div");
    step_content.classList.add("step-content", direction);
    step_content.innerHTML = STEP1_HTML;
    note_body.appendChild(step_content);
    setTimeout(() => step_content.style.transform = "translateX(0)", 10);
    initStep1();
}

function initStep1() {
    joinBtn = document.querySelector(".join");
    createBtn = document.querySelector(".create");
    question = document.querySelector(".question-text")
    
    joinBtn.addEventListener("click", clickGroupJoinBtn);
    createBtn.addEventListener("click", clickGroupCreateBtn);
}

function clickGroupJoinBtn(event) {
    changeStyle(joinBtn, createBtn);
    question.innerText = "다른 친구가 만든 스프링에 참여할까요?";
}

function clickGroupCreateBtn(event) {
    changeStyle(createBtn, joinBtn);
    question.innerText = "새로운 스프링을 만들까요?";
}

function changeStyle(selected, unselected) {
    selected.style.background = "#FC0";
    selected.children[0].children[0].style.display = "none";
    selected.children[0].children[1].style.display = "block";
    selected.children[0].children[2].style.color = "#FFF";

    unselected.style.background = "#FFF";
    unselected.children[0].children[0].style.display = "block";
    unselected.children[0].children[1].style.display = "none";
    unselected.children[0].children[2].style.color = "#767676";
}
