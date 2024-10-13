const note_body = document.querySelector(".note-body");
const step_bar = document.querySelector(".step-bar");
const stepIcons = Array.from(step_bar.children);
const confirm_btn = document.querySelector(".confirm-btn");
const steps = {
    1: {
        "draw": drawStep1,
        "confirm": confirmStep1
    },
    2: {
        "draw": drawStep1,
        "confirm": confirmStep1
    },
    3: {
        "draw": drawStep1,
        "confirm": confirmStep1
    },
    4: {
        "draw": drawStep1,
        "confirm": confirmStep1
    },
    5: {
        "draw": drawStep1,
        "confirm": confirmStep1
    }
}
const groupData = {
    "groupId": "",
    "groupName": "",
    "profileLocation": "",
    "nickname": ""
}

var currentStep = 1;

confirm_btn.addEventListener("click", confirmStep);
drawStep(currentStep);

function nextStep() {
    addStepIcon();
    removeStep(-1);
    drawStep(currentStep, "next");
}

function addStepIcon() {
    currentStep += 1;
    const stepIcon = stepIcons.find((stepIcon) => !stepIcon.classList.contains("fill"));
    stepIcon.classList.add("fill");
}

function removeStep(direction) {
    const step_content = note_body.children[0];
    step_content.style.transform = `translateX(${100 * direction}%)`;
    setTimeout(() => step_content.remove(), 300);
}

function drawStep(stepNumber, direction) {
    setTimeout(() => steps[stepNumber].draw(direction), 350);
}

function prevStep() {
    deleteStepIcon();
    removeStep(1);
    drawStep(currentStep, "prev");
}

function deleteStepIcon() {
    currentStep -= 1;
    const stepIcon = stepIcons.findLast((stepIcon) => stepIcon.classList.contains("fill"));
    stepIcon.classList.remove("fill");
}

function confirmStep(event) {
    steps[currentStep].confirm();
    nextStep();
}
