import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static final String HORIZONTAL_LINE = "____________________________________________________________";

    /**
     * Prints logo to standard out.
     */
    public static void printLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    /**
     * Prints greeting message to standard out.
     */
    public static void printGreetingMessage() {
        String greetingMessage = "Hello! I'm Duke\n"
                + "What can I do for you?";
        System.out.println(greetingMessage);
    }

    /**
     * Prints goodbye message to standard out.
     */
    public static void printGoodbyeMessage() {
        String goodbyeMessage = "Bye. Hope to see you again soon!";
        System.out.println(goodbyeMessage);
    }

    /**
     * Prints all tasks in list to standard out.
     *
     * @param tasks List of tasks.
     */
    public static void printTasks(ArrayList<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            int taskNumber = i + 1;
            Task task = tasks.get(i);
            String message = taskNumber + ".[";
            if (task.isComplete()) {
                message += "X";
            } else {
                message += " ";
            }
            message += "] " + task.getDescription();
            System.out.println(message);
        }
    }

    /**
     * Adds a task to list of tasks.
     *
     * @param tasks List of tasks.
     * @param description Task to add.
     */
    public static void addTask(ArrayList<Task> tasks, String description) {
        Task task = new Task(description);
        tasks.add(task);
        System.out.println("added: " + description);
    }

    /**
     * Marks a task from list as completed.
     *
     * @param tasks List of tasks.
     * @param taskNumber Task number of task as shown by the function {@link #printTasks(ArrayList)}.
     */
    public static void markTaskAsCompleted(ArrayList<Task> tasks, int taskNumber) {
        int index = taskNumber - 1;
        tasks.get(index).setComplete(true);
        String taskDescription = tasks.get(index).getDescription();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  [X] " + taskDescription);
    }

    /**
     * Marks a task from list as uncompleted.
     *
     * @param tasks List of tasks.
     * @param taskNumber Task number of task as shown by the function {@link #printTasks(ArrayList)}.
     */
    public static void markTaskAsUncompleted(ArrayList<Task> tasks, int taskNumber) {
        int index = taskNumber - 1;
        tasks.get(index).setComplete(false);
        String taskDescription = tasks.get(index).getDescription();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  [ ] " + taskDescription);
    }

    public static void main(String[] args) {
        printLogo();
        System.out.println(HORIZONTAL_LINE);
        printGreetingMessage();
        System.out.println(HORIZONTAL_LINE);

        ArrayList<Task> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println();
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                System.out.println(HORIZONTAL_LINE);
                printTasks(tasks);
                System.out.println(HORIZONTAL_LINE);
            } else if (input.startsWith("mark")) {
                int taskNumber = Integer.parseInt(input.replace("mark", "").trim());
                System.out.println(HORIZONTAL_LINE);
                markTaskAsCompleted(tasks, taskNumber);
                System.out.println(HORIZONTAL_LINE);
            } else if (input.startsWith("unmark")) {
                int taskNumber = Integer.parseInt(input.replace("unmark", "").trim());
                System.out.println(HORIZONTAL_LINE);
                markTaskAsUncompleted(tasks, taskNumber);
                System.out.println(HORIZONTAL_LINE);
            } else {
                System.out.println(HORIZONTAL_LINE);
                addTask(tasks, input);
                System.out.println(HORIZONTAL_LINE);
            }
        }
        scanner.close();

        System.out.println(HORIZONTAL_LINE);
        printGoodbyeMessage();
        System.out.println(HORIZONTAL_LINE);
    }
}
