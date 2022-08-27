import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
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
     * Prints line separator to standard out.
     */
    public static void printLineSeparator() {
        String lineSeperator = "____________________________________________________________";
        System.out.println(lineSeperator);
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
        printLineSeparator();
        printGreetingMessage();
        printLineSeparator();

        ArrayList<Task> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println();
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                break;
            }

            printLineSeparator();
            if (input.equals("list")) {
                printTasks(tasks);
            } else if (input.startsWith("mark")) {
                int taskNumber = Integer.parseInt(input.replace("mark", "").trim());
                markTaskAsCompleted(tasks, taskNumber);
            } else if (input.startsWith("unmark")) {
                int taskNumber = Integer.parseInt(input.replace("unmark", "").trim());
                markTaskAsUncompleted(tasks, taskNumber);
            } else {
                addTask(tasks, input);
            }
            printLineSeparator();
        }
        scanner.close();

        printLineSeparator();
        printGoodbyeMessage();
        printLineSeparator();
    }
}
