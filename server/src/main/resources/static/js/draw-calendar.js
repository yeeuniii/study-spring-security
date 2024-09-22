const table = document.querySelector("table");
const today = new Date();
const todayYear = today.getFullYear();
const todayMonth = today.getMonth() + 1;
const firstDay = new Date(todayYear, todayMonth - 1, 1).getDay();
const lastDate = new Date(todayYear, todayMonth, 0).getDate();

var date = 1;
var day = firstDay
var column = 0;
const trs = Array.from(table.children[0].children).slice(4)

while (date <= lastDate) {
    console.log(`date: ${date}, day: ${day}`);
    trs[column].children[day].innerHTML = makeCircle(date);
    date++;
    day++;
    if (day == 7) {
        day = 0;
        column++;
    }
}

function makeCircle(date) {
    return `<a class="day${date}" href="" style="width: 50px; height: 50px; border: 1px solid; border-radius: 50%; font-size: 24px; color: rgba(118, 118, 118, 0.7); display: flex; justify-content: center; align-items: center;">${date}</a>`
}


const days = document.querySelectorAll("a");

Array.from(days).forEach( day => {
    day.addEventListener("click", clickDayBtn);
})

function clickDayBtn(event) {
    event.preventDefault();
    alert(`${event.target.innerText}일 버튼을 눌렀습니다`);
}
