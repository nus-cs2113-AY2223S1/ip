import java.util.Scanner;

public class Duke {
    private static final String LOGO = " ________      ________ _____\n" +
            "|  ____\\ \\    / /  ____|  __ \\\n" +
            "| |__   \\ \\  / /| |__  | |__) |\n" +
            "|  __|   \\ \\/ / |  __| |  _  /\n" +
            "| |____   \\  /  | |____| | \\ \\\n" +
            "|______|   \\/   |______|_|  \\_\\";
    private static final String HORIZONTAL_LINE = "==================================================================";

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final TaskManager TASK_MANAGER = new TaskManager();
    private static final String[] COMMANDS = {"help", "list", "todo", "deadline", "event",
        "mark", "unmark", "bye"};

    private static void greet() {
        final String MESSAGE = "Hello! I'm Ever\n" +
                "What can I do for you?";
        System.out.println(LOGO);
        System.out.println(MESSAGE);
    }

    private static void exit() {
        final String MESSAGE = "Bye. Hope to see you again soon!";
        System.out.println(MESSAGE);
    }

    private static void displayCommandMenu() {
        for (String command: COMMANDS) {
            System.out.printf(" * %s\n", command);
        }
    }

    private static String getUserInput() {
        System.out.println(HORIZONTAL_LINE);
        return SCANNER.nextLine().trim();
    }

    private static void evaluateUserInput(String input) {
        String command = input.split(" ", 2)[0];

        switch (command) {
        case "help":
            displayCommandMenu();
            break;
        case "list":
            TASK_MANAGER.listTasks();
            break;
        case "mark": {
            TASK_MANAGER.markTaskAsDone(input);
            break;
        }
        case "unmark": {
            TASK_MANAGER.markTaskAsUndone(input);
            break;
        }
        case "todo":
            TASK_MANAGER.addTodoTask(input);
            break;
        case "deadline":
            TASK_MANAGER.addDeadlineTask(input);
            break;
        case "event":
            TASK_MANAGER.addEventTask(input);
            break;
        default:
            System.out.println("Sorry, I don't get what you mean. Can you try again?");
            break;
        }
    }

    public static void main(String[] args) {
        greet();

        while (true) {
            String inputMessage = getUserInput();
            if (inputMessage.equals("bye")) {
                break;
            }
            evaluateUserInput(inputMessage);
        }

        exit();
    }
}
