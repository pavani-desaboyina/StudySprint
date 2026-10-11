
import java.util.Scanner;

public class StudySprint {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskManager manager = new TaskManager();

        while (true) {
            System.out.println("\n===== STUDYSPRINT =====");
            System.out.println("1. Add Study Task");
            System.out.println("2. View All Tasks");
            System.out.println("3. Mark Task Completed");
            System.out.println("4. View Study Progress");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            if (!sc.hasNextInt()) {
                System.out.println("Please enter a number.");
                sc.nextLine();
                continue;
            }

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter task title: ");
                    String title = sc.nextLine().trim();

                    if (title.isEmpty()) {
                        System.out.println("Task title cannot be empty.");
                        break;
                    }

                    System.out.print("Enter deadline (e.g. 15-Oct-2026): ");
                    String deadline = sc.nextLine().trim();

                    if (deadline.isEmpty()) {
                        System.out.println("Deadline cannot be empty.");
                        break;
                    }

                    manager.addTask(title, deadline);
                    break;

                case 2:
                    manager.viewTasks();
                    break;

                case 3:
                    manager.viewTasks();
                    System.out.print("Enter task number to complete: ");

                    if (sc.hasNextInt()) {
                        manager.completeTask(sc.nextInt());
                        sc.nextLine();
                    } else {
                        System.out.println("Please enter a valid number.");
                        sc.nextLine();
                    }
                    break;

                case 4:
                    manager.showProgress();
                    break;

                case 5:
                    System.out.println("Thanks for using StudySprint!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice. Select 1-5.");
            }
        }
    }
}

