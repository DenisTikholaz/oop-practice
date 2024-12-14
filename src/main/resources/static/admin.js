const tokenInput = document.getElementById('token');
const lessonForm = document.getElementById('lessonForm');
const lessonIdInput = document.getElementById('lessonId');
const groupNameInput = document.getElementById('groupName');
const lessonNumberInput = document.getElementById('lessonNumber');
const dateInput = document.getElementById('date');
const coachInput = document.getElementById('coach');
const poolNumberInput = document.getElementById('poolNumber');
const lessonItems = document.getElementById('lessonItems');

// Function to fetch and display lessons
function fetchLessons(token) {
    fetch('/api/lessons', {
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${token}`
        }
    })
    .then(response => response.json())
    .then(lessons => {
        lessonItems.innerHTML = '';
        lessons.forEach(lesson => {
            const li = document.createElement('li');
            li.innerHTML = `
                ${lesson.groupName} - ${lesson.lessonNumber} - ${lesson.date} - ${lesson.coach} - Pool ${lesson.poolNumber}
                <button onclick="editLesson(${lesson.id})">Edit</button>
                <button onclick="deleteLesson(${lesson.id})">Delete</button>
            `;
            lessonItems.appendChild(li);
        });
    })
    .catch(error => alert('Error fetching lessons: ' + error));
}

// Function to handle form submission (create/edit lesson)
document.getElementById('lessonForm').addEventListener('submit', function (event) {
    event.preventDefault();

    const token = tokenInput.value;
    const lessonId = lessonIdInput.value;
    const lessonData = {
        groupName: groupNameInput.value,
        lessonNumber: lessonNumberInput.value,
        date: dateInput.value,
        coach: coachInput.value,
        poolNumber: poolNumberInput.value
    };

    let url = '/api/lessons';
    let method = 'POST';
    if (lessonId) {
        url = `/api/lessons/${lessonId}`;
        method = 'PUT';
    }

    fetch(url, {
        method: method,
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        },
        body: JSON.stringify(lessonData)
    })
    .then(response => {
        if (response.ok) {
            fetchLessons(token);
            clearForm();
        } else {
            alert('Failed to save lesson');
        }
    })
    .catch(error => alert('Error: ' + error));
});

// Function to edit a lesson
function editLesson(lessonId) {
    const token = tokenInput.value;
    fetch(`/api/lessons/${lessonId}`, {
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${token}`
        }
    })
    .then(response => response.json())
    .then(lesson => {
        lessonIdInput.value = lesson.id;
        groupNameInput.value = lesson.groupName;
        lessonNumberInput.value = lesson.lessonNumber;
        dateInput.value = lesson.date;
        coachInput.value = lesson.coach;
        poolNumberInput.value = lesson.poolNumber;
        lessonForm.style.display = 'block';
    })
    .catch(error => alert('Error fetching lesson: ' + error));
}

// Function to delete a lesson
function deleteLesson(lessonId) {
    const token = tokenInput.value;
    fetch(`/api/lessons/${lessonId}`, {
        method: 'DELETE',
        headers: {
            'Authorization': `Bearer ${token}`
        }
    })
    .then(response => {
        if (response.ok) {
            fetchLessons(token);
        } else {
            alert('Failed to delete lesson');
        }
    })
    .catch(error => alert('Error: ' + error));
}

// Function to clear the form
function clearForm() {
    lessonIdInput.value = '';
    groupNameInput.value = '';
    lessonNumberInput.value = '';
    dateInput.value = '';
    coachInput.value = '';
    poolNumberInput.value = '';
    lessonForm.style.display = 'none';
}

// Function to validate token and show admin panel
tokenInput.addEventListener('input', function () {
    const token = tokenInput.value;
    if (token) {
        fetchLessons(token);
        lessonForm.style.display = 'block';
    } else {
        lessonForm.style.display = 'none';
    }
});
