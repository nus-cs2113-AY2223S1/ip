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
    public static void printTasks(ArrayList<String> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            int taskNumber = i + 1;
            String task = tasks.get(i);
            System.out.println(taskNumber + ". " + task);
        }
    }

    /**
     * Adds a task to list of tasks.
     *
     * @param tasks List of tasks.
     * @param task  Task to add.
     */
    public static void addTask(ArrayList<String> tasks, String task) {
        tasks.add(task);
        System.out.println("added: " + task);
    }

    public static void main(String[] args) {
        printLogo();
        System.out.println(HORIZONTAL_LINE);
        printGreetingMessage();
        System.out.println(HORIZONTAL_LINE);

        ArrayList<String> tasks = new ArrayList<>();
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
