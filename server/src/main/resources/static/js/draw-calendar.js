const table = document.querySelector("table");
const year = document.querySelector(".year");
const month = document.querySelector(".month");
const trs = Array.from(table.children[0].children).slice(4);
const today = new Date();

function init() {
    year.innerText = today.getFullYear();
    month.innerText = today.getMonth() + 1;
    console.log(year.innerText, month.innerText);

    drawDateOfCalendar();
}

init();

async function drawDateOfCalendar() {
    const firstDay = new Date(year.innerText, month.innerText - 1, 1).getDay();
    const lastDate = new Date(year.innerText, month.innerText, 0).getDate();

    let date = 1;
    let day = firstDay
    let column = 0;

    const days = await fetch(`/api/diary/monthly?year=${year.innerText}&month=${month.innerText}`)
        .then(response => response.json())
        .then(data => data.days);
    const diaryDays = days.map(day => Number(day.date))

    while (date <= lastDate) {
        trs[column].children[day].innerHTML = makeCircle(date, diaryDays);
        date++;
        day++;
        if (day === 7) {
            day = 0;
            column++;
        }
    }

    addEvents();
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

function makeCircle(date, diaryDays) {
    if (diaryDays.includes(date)) {
        return `<a class="date day${date} diary" href="/api/diary">${date}</a>`;
    }
    if (isToday(date)) {
        return `<a class="date day${date}" href="/diary">${date}</a>`;
    }
    return `<span class="date day${date}">${date}</span>`;
}

function isToday(date) {
    if (today.getFullYear() !== year.innerText) {
        return false
    }
    if (today.getMonth() !== month.innerText - 1) {
        return false
    }
    return today.getDate() === date;
}



function addEvents() {
    const diaryDays = document.querySelectorAll("a.diary");
    Array.from(diaryDays).forEach( date => {
        date.addEventListener("click", showDiary);
    })
}

function showDiary(event) {
    event.preventDefault();
    const url = event.target.href
    fetch(`${url}?year=${year.innerText}&month=${month.innerText}&day=${event.target.innerText}`)
        .then(response => response.json())
        .then(data => window.location.href = `${url}/${data.diaryId}`);
}
