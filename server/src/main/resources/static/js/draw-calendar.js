const table = document.querySelector("table");
const year = document.querySelector(".year");
const month = document.querySelector(".month");
const trs = Array.from(table.children[0].children).slice(4);
const today = new Date();

year.innerText = today.getFullYear();
month.innerText = today.getMonth() + 1;
console.log(year.innerText, month.innerText);

drawDateOfCalendar();

function drawDateOfCalendar() {
    const firstDay = new Date(year.innerText, month.innerText - 1, 1).getDay();
    const lastDate = new Date(year.innerText, month.innerText, 0).getDate();

    let date = 1;
    let day = firstDay
    let column = 0;

    while (date <= lastDate) {
        trs[column].children[day].innerHTML = makeCircle(date);
        date++;
        day++;
        if (day === 7) {
            day = 0;
            column++;
        }
    }
}

function clearDate() {
    let column = 0;
    let row = 0;

    while (column < 5) {
        trs[column].children[row].innerText = "";
        row++;
        if (row === 7) {
            column++;
            row = 0;
        }
    }
}

function makeCircle(date) {
    return `<a class="date day${date}" href="" style="width: 50px; height: 50px; border: 1px solid; border-radius: 50%; font-size: 24px; color: rgba(118, 118, 118, 0.7); display: flex; justify-content: center; align-items: center;">${date}</a>`;
}


const days = document.querySelectorAll("a.date");

Array.from(days).forEach( date => {
    date.addEventListener("click", clickDayBtn);
})

function clickDayBtn(event) {
    event.preventDefault();
    alert(`${event.target.innerText}일 버튼을 눌렀습니다`);
}

const leftArrow = document.querySelector(".left-arrow");
const rightArrow = document.querySelector(".right-arrow");

leftArrow.addEventListener("click", clickLeftArrowButton);
rightArrow.addEventListener("click", clickRightArrowButton);

function clickLeftArrowButton(event) {
    event.preventDefault();
    month.innerText -= 1;
    if (month.innerText === "0") {
        month.innerText = 12;
        year.innerText -= 1;
    }
    clearDate();
    drawDateOfCalendar()
}

function clickRightArrowButton(event) {
    event.preventDefault();
    month.innerText = Number(month.innerText) + 1;
    if (month.innerText === "13") {
        month.innerText = 1;
        year.innerText = Number(year.innerText) + 1;
    }
    clearDate();
    drawDateOfCalendar()
}
