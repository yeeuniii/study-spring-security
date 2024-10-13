const note_body = document.querySelector(".note-body");
const step_bar = document.querySelector(".step-bar");
const stepIcons = Array.from(step_bar.children);
const steps = {
    1: drawStep1,
    2: drawStep1,
    3: drawStep1,
    4: drawStep1,
    5: drawStep1
}

var currentStep = 1;

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
    setTimeout(() => steps[stepNumber](direction), 350);
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

drawStep(currentStep);
