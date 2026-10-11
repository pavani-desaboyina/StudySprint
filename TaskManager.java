
import java.util.ArrayList;

public class TaskManager {
    private final ArrayList<Task> tasks = new ArrayList<>();

    public void addTask(String title, String deadline) {
        tasks.add(new Task(title, deadline));
        System.out.println("Task added successfully!");
    }

    public void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks added yet.");
            return;
        }

        System.out.println("\n--- Your Study Tasks ---");

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            String status = task.completed ? "Completed" : "Pending";

            System.out.println(
                (i + 1) + ". " + task.title
                + " | Deadline: " + task.deadline
                + " | Status: " + status
            );
        }
    }

    public void completeTask(int number) {
        if (number < 1 || number > tasks.size()) {
            System.out.println("Invalid task number.");
            return;
        }

        tasks.get(number - 1).completed = true;
        System.out.println("Task marked as completed!");
    }

    public void showProgress() {
        int completed = 0;

        for (Task task : tasks) {
            if (task.completed) {
                completed++;
            }
        }

        System.out.println("\n--- Study Progress ---");
        System.out.println("Total tasks: " + tasks.size());
        System.out.println("Completed: " + completed);
        System.out.println("Pending: " + (tasks.size() - completed));

        if (!tasks.isEmpty()) {
            double percentage = completed * 100.0 / tasks.size();
            System.out.printf("Completion: %.1f%%%n", percentage);
        }
    }
}
