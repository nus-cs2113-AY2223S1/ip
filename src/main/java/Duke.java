import java.util.Map;
import static java.util.Map.entry;
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

    public static final Map<String, Command> COMMANDS = Map.ofEntries(
            entry("help", new Command("help", "Get help for the commands supported and their syntax")),
            entry("list", new Command("list", "View all tasks with their task number")),
            entry("todo", new Command("todo <description>", "Add a todo task")),
            entry("deadline", new Command("deadline <description> /by <deadline-datetime>", "Add a task with its deadline")),
            entry("event", new Command("event <description> /at <event-datetime>", "Add an event with its date and time")),
            entry("mark", new Command("mark <task-number>", "Mark a task as done")),
            entry("unmark", new Command("unmark <task-number>", "Unmark a task from done")),
            entry("bye", new Command("bye", "Exit the application"))
    );

    private static void greet() {
        System.out.println(LOGO);
        System.out.println(Message.GREETING_MESSAGE);
    }

    private static void exit() {
        System.out.println(Message.EXIT_MESSAGE);
    }

    private static void displayCommandMenu() {
        for (Map.Entry<String, Command> command: COMMANDS.entrySet()) {
            System.out.println();
            System.out.println(command.getValue());
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
        case "mark":
            TASK_MANAGER.markTaskAsDone(input);
            break;
        case "unmark":
            TASK_MANAGER.markTaskAsUndone(input);
            break;
        case "todo":
            TASK_MANAGER.addTodoTask(input);
            break;
        case "deadline":
            TASK_MANAGER.addDeadlineTask(input);
            break;
        case "event":
            TASK_MANAGER.addEventTask(input);
            break;
        case "":
            System.out.println(Message.EMPTY_INPUT_MESSAGE + " " + Message.HELP_MESSAGE);
            break;
        default:
            System.out.println(Message.INVALID_COMMAND_MESSAGE + " " + Message.HELP_MESSAGE);
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
