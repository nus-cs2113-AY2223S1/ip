package duke;

import duke.task.TaskManager;

import java.util.LinkedHashMap;
import java.util.Map;
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

    public static final Map<String, Command> COMMANDS = new LinkedHashMap<>();

    private static void initCommand() {
        COMMANDS.put("help", new Command("help", "Get help for the commands supported and their syntax"));
        COMMANDS.put("list", new Command("list", "View all tasks with their task number"));
        COMMANDS.put("todo", new Command("todo <description>", "Add a todo task"));
        COMMANDS.put("deadline", new Command("deadline <description> /by <deadline-datetime>", "Add a task with its deadline"));
        COMMANDS.put("event", new Command("event <description> /at <event-datetime>", "Add an event with its date and time"));
        COMMANDS.put("mark", new Command("mark <task-number>", "Mark a task as done"));
        COMMANDS.put("unmark", new Command("unmark <task-number>", "Unmark a task from done"));
        COMMANDS.put("delete", new Command("delete <task-number>", "Delete a task from the list"));
        COMMANDS.put("bye", new Command("bye", "Exit the application"));
    }

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
        case "delete":
            TASK_MANAGER.deleteTask(input);
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
        initCommand();

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
