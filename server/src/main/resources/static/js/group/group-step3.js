const STEP3_HTML = `<div style="width: 100%; height: 34px;">
                                <span class="subject">나의 캐릭터를 골라요.</span>
                            </div>
                            <div class="first-line">
                                <a id="pring2" href="#" class="first character">
                                    <img src="/images/group/character/pring2.svg" class="character">
                                </a>
                                <a id="pring3" href="#" class="last character">
                                    <img src="/images/group/character/pring3.svg" class="character">
                                </a>
                            </div>
                            <div class="second-line">
                                <a id="pring1" href="#" class="first character">
                                    <img src="/images/group/character/pring1.svg" class="character">
                                </a>
                                <a id="pring7" href="#" class="mid character">
                                    <img src="/images/group/character/pring7.svg" class="character">
                                </a>
                                <a id="pring4" href="#" class="last green">
                                    <img src="/images/group/character/pring4.svg" class="green">
                                </a>
                            </div>
                            <div class="third-line">
                                <a id="pring5" href="#" class="first blue" style="position: relative; bottom: 14px;">
                                    <img src="/images/group/character/pring5.svg" class="blue">
                                </a>
                                <a id="pring6" href="#" class="last character">
                                    <img src="/images/group/character/pring6.svg" class="character">
                                </a>
                            </div>`;

function drawStep3(direction) {
    const step_content = document.createElement("div");
    step_content.classList.add("step-content", direction);
    step_content.innerHTML = STEP3_HTML;
    note_body.appendChild(step_content);
    setTimeout(() => step_content.style.transform = "translateX(0)", 10);
    initStep3();
}

function initStep3() {
    viewSelectableCharacter();
}

async function viewSelectableCharacter() {
    const groupId = window.localStorage.getItem("groupId");

    const selectedImages = await fetch(`/api/groups/${groupId}/profile-image`)
        .then(response => response.json())
        .then(data => data.selectedImages);

    selectedImages.forEach(image => {
        const profileLocation = image.profileLocation
        const index = profileLocation.indexOf("pring");
        const name = profileLocation.substr(index, 6);
        const pring = document.getElementById(name);

        pring.removeAttribute("href");
        pring.children[0].src = `/images/group/character/gray-${name}.svg`;
    });
}

function confirmStep3() {

}
