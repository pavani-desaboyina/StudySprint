
const API_URL = "http://localhost:8080/api/tasks";

const taskForm = document.getElementById("taskForm");
const taskTitle = document.getElementById("taskTitle");
const taskDeadline = document.getElementById("taskDeadline");
const tasksContainer = document.getElementById("tasksContainer");

async function loadTasks() {
    try {
        const response = await fetch(API_URL);

        if (!response.ok) {
            throw new Error("Could not load tasks");
        }

        const tasks = await response.json();
        displayTasks(tasks);
        updateSummary(tasks);
    } catch (error) {
        tasksContainer.textContent =
            "Java backend is not connected yet. Start the backend to load tasks.";
    }
}

function displayTasks(tasks) {
    tasksContainer.replaceChildren();

    if (tasks.length === 0) {
        tasksContainer.textContent = "No tasks yet. Add your first task!";
        return;
    }

    tasks.forEach(task => {
        const item = document.createElement("div");
        item.className = "task-item";

        if (task.completed) {
            item.classList.add("completed");
        }

        const details = document.createElement("div");

        const title = document.createElement("h3");
        title.textContent = task.title;

        const deadline = document.createElement("p");
        deadline.textContent = "Deadline: " + task.deadline;

        const status = document.createElement("p");
        status.className = "status";
        status.textContent = task.completed ? "Completed" : "Pending";

        details.append(title, deadline, status);
        item.appendChild(details);

        if (!task.completed) {
            const button = document.createElement("button");
            button.textContent = "Mark Complete";
            button.addEventListener("click", () => completeTask(task.id));
            item.appendChild(button);
        }

        tasksContainer.appendChild(item);
    });
}

function updateSummary(tasks) {
    const completed = tasks.filter(task => task.completed).length;

    document.getElementById("totalTasks").textContent = tasks.length;
    document.getElementById("completedTasks").textContent = completed;
    document.getElementById("pendingTasks").textContent =
        tasks.length - completed;
}

taskForm.addEventListener("submit", async event => {
    event.preventDefault();

    const task = {
        title: taskTitle.value.trim(),
        deadline: taskDeadline.value,
        completed: false
    };

    if (!task.title || !task.deadline) {
        alert("Please enter a task title and deadline.");
        return;
    }

    try {
        const response = await fetch(API_URL, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(task)
        });

        if (!response.ok) {
            throw new Error("Could not add task");
        }

        taskForm.reset();
        await loadTasks();
    } catch (error) {
        alert("Could not add task. Please check the Java backend.");
    }
});

async function completeTask(id) {
    try {
        const response = await fetch(`${API_URL}/${id}/complete`, {
            method: "PUT"
        });

        if (!response.ok) {
            throw new Error("Could not complete task");
        }

        await loadTasks();
    } catch (error) {
        alert("Could not update task. Please check the Java backend.");
    }
}

loadTasks();
