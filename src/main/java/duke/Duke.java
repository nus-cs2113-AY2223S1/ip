package duke;

import duke.exceptions.IncorrectFormatException;
import duke.exceptions.UnrecognizedCommandException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Todo;

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
        boolean validFormat;

        while (!command.equals("bye")) {
            printLine();
            validFormat = false;
            try {
                validateFormat(command, parameters);
                validFormat = true;
            } catch (NumberFormatException e) {
                System.out.println("\tIncorrect format! Please enter a valid integer number to mark a task as done or not done!");
            } catch (IncorrectFormatException e) {
                System.out.println("\t" + e.getMessage());
            }
            if (validFormat) {
                executeCommand(command, parameters);
            }
            printLine();
            input = acceptAndValidateInput();
            command = retrieveCommand(input);
            parameters = retrieveParameters(input);
        }
        exit();
    }

    public static void executeCommand(String command, String parameters) {
        String deadline;
        String description;
        int taskNumber;

        switch (command) {
        case "help":
            taskManager.printHelpMessage();
            break;
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
        case "deadline":
            description = retrieveTaskDescription(parameters, TaskManager.DEADLINE_SEPERATOR);
            deadline = retrieveTime(parameters, TaskManager.DEADLINE_SEPERATOR);
            taskManager.addTask(new Deadline(description, deadline));
            break;

        case "event":
            description = retrieveTaskDescription(parameters, TaskManager.EVENT_SEPERATOR);
            deadline = retrieveTime(parameters, TaskManager.EVENT_SEPERATOR);
            taskManager.addTask(new Event(description, deadline));
            break;
        }
    }

    public static void validateFormat(String command, String parameters) throws IncorrectFormatException, NumberFormatException {
        if (command.equals("list"))
            return;
        if (command.equals("mark") || command.equals("unmark")) {
            if (parameters.isEmpty()) {
                throw new IncorrectFormatException("Incorrect format! The task number that is to be marked as done/not done cannot be empty!");
            }
            retrieveTaskNumber(parameters);
        }
        if (command.equals("deadline")) {
            handlePossibleDeadlineExceptions(parameters);
        }
        if (command.equals("event")) {
            handlePossibleEventExceptions(parameters);
        }
        if (parameters.isEmpty()) {
            throw new IncorrectFormatException("Incorrect format! The description of a todo cannot be empty!");
        }
    }

    public static void handlePossibleEventExceptions(String parameters) throws IncorrectFormatException {
        if (!parameters.contains(TaskManager.EVENT_SEPERATOR.trim())) {
            throw new IncorrectFormatException("Incorrect format! Event must contain a task and its time separated by '/at'! Refer to 'help' for more details!");
        }
        if (parameters.trim().equals(TaskManager.EVENT_SEPERATOR.trim())) {
            throw new IncorrectFormatException("Incorrect format! Event must contain a task and its time separated by '/at'! Refer to 'help' for more details!");
        }
        if (retrieveTaskDescription(parameters, TaskManager.EVENT_SEPERATOR.trim()).isEmpty()) {
            throw new IncorrectFormatException("Incorrect format! The description of an event cannot be empty!");
        }
        if (retrieveTime(parameters, TaskManager.EVENT_SEPERATOR).isEmpty()) {
            throw new IncorrectFormatException("Incorrect format! The time of an event cannot be empty!");
        }
    }

    public static void handlePossibleDeadlineExceptions(String parameters) throws IncorrectFormatException {
        if (!parameters.contains(TaskManager.DEADLINE_SEPERATOR.trim())) {
            throw new IncorrectFormatException("Incorrect format! Deadline must contain a task and its deadline separated by '/by'! Refer to 'help' for more details!");
        }
        if (parameters.trim().equals(TaskManager.DEADLINE_SEPERATOR.trim())) {
            throw new IncorrectFormatException("Incorrect format! Deadline must contain a task and its deadline separated by '/by'! Refer to 'help' for more details!");
        }
        if (retrieveTaskDescription(parameters, TaskManager.DEADLINE_SEPERATOR.trim()).isEmpty()) {
            throw new IncorrectFormatException("Incorrect format! The description of a deadline cannot be empty!");
        }
        if (retrieveTime(parameters, TaskManager.DEADLINE_SEPERATOR).isEmpty()) {
            throw new IncorrectFormatException("Incorrect format! The deadline of a deadline cannot be empty!");
        }
    }

    public static int retrieveTaskNumber(String input) throws NumberFormatException {
        return Integer.parseInt(input) - 1;
    }

    public static String retrieveParameters(String input) {
        String[] parsed = input.split(" ", 2);
        if (parsed.length > 1) {
            return parsed[1].trim();
        }
        return "";
    }

    public static String retrieveTime(String parameters, String separator) {
        String[] parsed = parameters.split(separator);
        if (parsed.length > 1) {
            return parsed[1];
        }
        return "";
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
        return scanner.nextLine().trim();

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
