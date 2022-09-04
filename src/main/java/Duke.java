import java.util.Scanner;

public class Duke {

    static TaskManager taskManager = new TaskManager();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        greet();
        processInput();
    }

    public static void printLine() {
        System.out.print("\t");
        for (int i = 0; i < 60; i++) {
            System.out.print("\u2500");
        }
        System.out.println();
    }

    public static void greet() {
        printLine();
        System.out.println("\tHello! I'm Duke!");
        System.out.println("\tWhat can I do for you?");
        printLine();
    }

    public static void processInput() {
        String input = acceptAndValidateInput();
        String command = retrieveCommand(input);
        String parameters = retrieveParameters(input);
        String description, deadline;
        int taskNumber;

        while (!command.equals("bye")) {
            printLine();
            switch (command) {
            case "list":
                taskManager.listTasks();
                break;
            case "mark":
                taskNumber = retrieveTaskNumber(parameters);
                taskManager.markTaskAsDone(taskNumber);
                break;
            case "unmark":
                taskNumber = retrieveTaskNumber(parameters);
                taskManager.markTaskAsUndone(taskNumber);
                break;
            case "todo":
                taskManager.addTask(new Todo(parameters));
                break;
            case "deadline": {
                description = retrieveTaskDescription(parameters, TaskManager.DEADLINE_SEPERATOR);
                deadline = retrieveTime(parameters, TaskManager.DEADLINE_SEPERATOR);
                taskManager.addTask(new Deadline(description, deadline));
                break;
            }
            case "event": {
                description = retrieveTaskDescription(parameters, TaskManager.EVENT_SEPERATOR);
                deadline = retrieveTime(parameters, TaskManager.EVENT_SEPERATOR);
                taskManager.addTask(new Event(description, deadline));
                break;
            }
            default: //catch-all to prevent abrupt errors
                taskManager.addTask(new Task(input));
                break;
            }
            printLine();
            input = acceptAndValidateInput();
            command = retrieveCommand(input);
            parameters = retrieveParameters(input);
        }
        exit();
    }

    public static int retrieveTaskNumber(String input) {
        return Integer.parseInt(input) - 1;
    }

    public static String retrieveParameters(String input) {
        String[] parsed = input.split(" ", 2);
        if (parsed.length > 1) return parsed[1];
        else return "";
    }

    public static String retrieveTime(String parameters, String separator) {
        return parameters.split(separator)[1];
    }

    public static String retrieveTaskDescription(String parameters, String separator) {
        return parameters.split(separator)[0];
    }

    public static String acceptAndValidateInput() {
        boolean isInputValid = false;
        String input = "";
        String command = "";
        while (!isInputValid) {
            try {
                input = readLine();
                command = retrieveCommand(input);
                validateCommand(command);
                isInputValid = true;
            } catch (UnrecognizedCommandException e) {
                printLine();
                System.out.println("\t" + e.getMessage());
                printLine();
            }
        }
        return input;
    }

    public static String readLine() {
        return scanner.nextLine();
    }

    public static String retrieveCommand(String input) {
        return input.split(" ")[0];
    }

    public static void validateCommand(String command) throws UnrecognizedCommandException {
        if (!TaskManager.isValidCommand(command)) {
            throw new UnrecognizedCommandException();
        }
    }


    public static void exit() {
        printLine();
        System.out.println("\t" + "Bye. Hope to see you again soon!");
        printLine();
    }
}
