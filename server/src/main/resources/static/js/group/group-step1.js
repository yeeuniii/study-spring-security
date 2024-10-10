joinBtn = document.querySelector(".join");
createBtn = document.querySelector(".create");
question = document.querySelector(".question-text")

joinBtn.addEventListener("click", clickGroupJoinBtn);
createBtn.addEventListener("click", clickGroupCreateBtn);

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
