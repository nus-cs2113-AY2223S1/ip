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

            String[] inputArray = input.split(" ", 2);
            String command = inputArray[0];
            String argument;
            if (inputArray.length == 2) {
                argument = inputArray[1];
            } else {
                argument = "";
            }

            printLineSeparator();
            if (command.equals("list")) {
                System.out.println("Here are the tasks in your list:");

                taskManager.printTasks();
            } else if (command.equals("todo")) {
                System.out.println("Got it. I've added this task:");

                Todo todo = new Todo(argument);
                taskManager.addTask(todo);
                todo.print();

                int numTasks = taskManager.getNumTasks();
                System.out.println("Now you have " + numTasks + " tasks in the list.");
            } else if (command.equals("deadline")) {
                System.out.println("Got it. I've added this task:");

                String[] argumentArray = argument.split("/by");
                String description = argumentArray[0].trim();
                String by = argumentArray[1].trim();

                Deadline deadline = new Deadline(description, by);
                taskManager.addTask(deadline);
                deadline.print();

                int numTasks = taskManager.getNumTasks();
                System.out.println("Now you have " + numTasks + " tasks in the list.");
            } else if (command.equals("event")) {
                System.out.println("Got it. I've added this task:");

                String[] argumentArray = argument.split("/at");
                String description = argumentArray[0].trim();
                String at = argumentArray[1].trim();

                Event event = new Event(description, at);
                taskManager.addTask(event);
                event.print();

                int numTasks = taskManager.getNumTasks();
                System.out.println("Now you have " + numTasks + " tasks in the list.");
            } else if (command.equals("mark")) {
                int taskNumber = Integer.parseInt(argument);
                taskManager.markTaskAsCompleted(taskNumber);
            } else if (command.equals("unmark")) {
                int taskNumber = Integer.parseInt(argument);
                taskManager.markTaskAsUncompleted(taskNumber);
            } else {
                System.out.println();
            }
            printLineSeparator();
        }
        scanner.close();

        printLineSeparator();
        printGoodbyeMessage();
        printLineSeparator();
    }
}
