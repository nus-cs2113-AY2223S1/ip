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
        String lineSeparator = "____________________________________________________________";
        System.out.println(lineSeparator);
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
     * Initializes application and starts the interaction with user.
     */
    public static void main(String[] args) {
        printLogo();
        printLineSeparator();
        printGreetingMessage();
        printLineSeparator();

        TaskManager taskManager = new TaskManager();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println();
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                break;
            }

            printLineSeparator();
            if (input.equals("list")) {
                taskManager.printTasks();
            } else if (input.startsWith("mark")) {
                int taskNumber = Integer.parseInt(input.replace("mark", "").trim());
                taskManager.markTaskAsCompleted(taskNumber);
            } else if (input.startsWith("unmark")) {
                int taskNumber = Integer.parseInt(input.replace("unmark", "").trim());
                taskManager.markTaskAsUncompleted(taskNumber);
            } else {
                taskManager.addTask(input);
            }
            printLineSeparator();
        }
        scanner.close();

        printLineSeparator();
        printGoodbyeMessage();
        printLineSeparator();
    }
}
