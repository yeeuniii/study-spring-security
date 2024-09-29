const date = document.querySelector(".date")
const today = new Date();

function init() {
    date.innerText = `${today.getFullYear()}.${today.getMonth() + 1}.${today.getDate()}`;
}

init();