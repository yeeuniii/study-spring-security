const codeBox = document.querySelector('.code-box');
const code = document.querySelector('.code');

code.addEventListener("input", checkGroupCode)

function checkGroupCode() {
    codeBox.style.border = "0.5px solid #767676";
    if (code.value !== "") {
        codeBox.style.border = "2px solid #FC0";
    }
}
