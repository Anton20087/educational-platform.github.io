const gradesData = {
    "2024-05-28": [
        { course: "Англійська", grade: 11, comment: "" },
        { course: "Алгебра", grade: 11, comment: "" },
        { course: "Геометрія", grade: 11, comment: "" },
        { course: "Хімія", grade: 11, comment: "" },
        { course: "Фізика", grade: 11, comment: "" },
        { course: "Історія України", grade: 11, comment: "" },
        { course: "Всесвітня історія", grade: 11, comment: "" },
        { course: "Українська мова", grade: 11, comment: "" },
        { course: "Українська література", grade: 11, comment: "" },
        { course: "Зарубіжна література", grade: 11, comment: "" }
    ],

};

let currentDate = new Date();
const today = new Date();

function getMonday(d) {
    d = new Date(d);
    const day = d.getDay(),
        diff = d.getDate() - day + (day === 0 ? -6 : 1);
    return new Date(d.setDate(diff));
}

function formatDate(date) {
    return date.toISOString().split('T')[0];
}

function renderTables(date) {
    const monday = getMonday(date);
    const weekDates = [];

    for (let i = 0; i < 5; i++) {
        const day = new Date(monday);
        day.setDate(monday.getDate() + i);
        weekDates.push(formatDate(day));
    }

    document.getElementById('week-dates').textContent = `${weekDates[0]} - ${weekDates[4]}`;
    const tablesContainer = document.getElementById('grades-tables');
    tablesContainer.innerHTML = '';

    weekDates.forEach(date => {
        const grades = gradesData[date] || [];
        let tableHTML = `
                    <h4>${date}</h4>
                    <table>
                        <thead>
                            <tr>
                                <th>Курс</th>
                                <th>Оцінка</th>
                                <th>Зауваження</th>
                            </tr>
                        </thead>
                        <tbody>
                `;

        grades.forEach(grade => {
            tableHTML += `
                        <tr>
                            <td>${grade.course}</td>
                            <td>${grade.grade}</td>
                            <td>${grade.comment}</td>
                        </tr>
                    `;
        });

        tableHTML += `
                        </tbody>
                    </table>
                `;

        tablesContainer.innerHTML += tableHTML;
    });
}

function previousWeek() {
    currentDate.setDate(currentDate.getDate() - 7);
    renderTables(currentDate);
}

function nextWeek() {
    currentDate.setDate(currentDate.getDate() + 7);
    renderTables(currentDate);
}

function goToCurrentWeek() {
    currentDate = new Date(today);
    renderTables(currentDate);
}

document.addEventListener('DOMContentLoaded', () => {
    renderTables(currentDate);
});