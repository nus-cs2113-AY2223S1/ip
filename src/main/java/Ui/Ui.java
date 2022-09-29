package Ui;

import Tasks.TaskList;
import Parser.Command;
import java.io.PrintStream;
import java.util.Scanner;
import java.io.InputStream;

public class Ui {
    private static final String SEPARATOR = "____________________________________________________________";
    private static final String DUKE_LOGO = " ____        _\n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String CONV_START = "Hello! I'm Duke";
    private static final String CONV_END = "Bye. Hope to see you again soon!";
    private static final String MESSAGE_INVALID_TASK_NUMBER = "OOPS, that task is not in the list.";
    private static final String MESSAGE_EMPTY_ACTION_ARGS = "OOPS!!! The description of the action cannot be empty.";
    private static final String MESSAGE_UNKNOWN = "OOPS!!! I'm sorry, but I don't know what that means :-(";
    private static final String FILE_NOT_FOUND = "File not found. "
            + "Make sure the data file is located in './data/data.md'"
            + "\nSaving services disabled.";
    private static final String FILE_CORRUPTED = "File data corrupted. "
            + "Please check data file again or delete entire content."
            + "\nExiting program.";
    private static final String USER_QUERY = "What can I do for you?";

    private final PrintStream out;
    private final Scanner in;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public String getUserInput() {
        out.println(USER_QUERY);
        String userInput = in.nextLine();
        while (userInput.trim().isEmpty()) {
            userInput = in.nextLine();
        }
        reprintUserInput(userInput);
        return userInput;
    }

    public void printOutput(String message) {
        out.println(SEPARATOR);
        out.println(message);
        out.println(SEPARATOR);
    }

    public void printError(String message) {
        out.print("\u001b[31m"); // red font ANSI
        out.println(SEPARATOR);
        out.println(message);
        out.println(SEPARATOR);
        out.println("\u001b[0m"); // reset font ANSI
    }

    private void reprintUserInput(String message) {
        out.print("\u001b[32m"); // green font ANSI
        out.println("[Entered: " + message + " ]");
        out.println("\u001b[0m");
    }

    public void printAllTask(TaskList taskList) {
        printOutput(taskList.getCompleteList());
    }

    public void showResult(String item, Command command, int size) {
        switch(command) {
            case TODO:
                printAddActionResult(item, size, "Todo");
                break;
            case EVENT:
                printAddActionResult(item, size, "Event");
                break;
            case DEADLINE:
                printAddActionResult(item, size, "Deadline");
                break;
            case MARK:
                printMarkUnmarkActionResult(item, "done");
                break;
            case UNMARK:
                printMarkUnmarkActionResult(item, "not done");
                break;
            case DELETE:
                printDeleteActionResult(item, size);
                break;
            case FIND:
                printFindActionResult(item);
                break;
            default:
                break;
        }
    }

    public void printFindActionResult(String item) {
        String output = "";
        if (item.isEmpty()) {
            output += "Oops, I can't seem to find that task.";
        } else {
            output += "Here are the matching tasks in your list: \n"
                    + item;
        }
        printOutput(output);
    }

    public void printAddActionResult(String item, int size, String command) {
        String output = "I got you, added a "
                + command + ":\n"+  item
                + "\nNow you have " + size + " tasks in the list.";
        printOutput(output);
    }

    public void printDeleteActionResult(String item, int size) {
        String output = "Noted. I've removed this task:\n"
                + item
                + "\n Now you have " + size + " tasks in the list.";
        printOutput(output);
    }

    public void printMarkUnmarkActionResult(String item, String status) {
        String output = "Nice! I've marked this task as "
                + status + ":\n" + item;
        printOutput(output);
    }

    public void printFileNotFound() {
        printOutput(FILE_NOT_FOUND);
    }

    public void printFileCorrupted() {
        printOutput(FILE_CORRUPTED);
    }

    public void printWelcomeMessage() {
        printOutput(DUKE_LOGO + CONV_START);
    }

    public void printExitMessage() {
        printOutput(CONV_END);
    }

    public void printInvalidTaskNumber() {
        printError(MESSAGE_INVALID_TASK_NUMBER);
    }

    public void printEmptyActionArgs() {
        printError(MESSAGE_EMPTY_ACTION_ARGS);
    }

    public void printUnknownMessage() {
        printError(MESSAGE_UNKNOWN);
    }

}
