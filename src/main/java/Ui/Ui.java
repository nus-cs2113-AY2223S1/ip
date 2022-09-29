package Ui;

import Tasks.TaskList;
import Parser.Command;
import java.io.PrintStream;
import java.util.Scanner;
import java.io.InputStream;

/**
 * Represents the interface class to interact with the user.
 * Supports printing messages and errors(in red).
 */
public class Ui {
    // Common Messages
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

    /**
     * Return the input entered by the user on the terminal.
     *
     * @return String user input.
     */
    public String getUserInput() {
        out.println(USER_QUERY);
        String userInput = in.nextLine();
        while (userInput.trim().isEmpty()) {
            userInput = in.nextLine();
        }
        reprintUserInput(userInput);
        return userInput;
    }

    /**
     * Prints the message into the stream with Separator in between.
     *
     * @param message String to output.
     */
    public void printOutput(String message) {
        out.println(SEPARATOR);
        out.println(message);
        out.println(SEPARATOR);
    }

    /**
     * Prints error messages (in red) into the stream with separator in between.
     *
     * @param message Error string to output.
     */
    public void printError(String message) {
        out.print("\u001b[31m"); // red font ANSI
        out.println(SEPARATOR);
        out.println(message);
        out.println(SEPARATOR);
        out.println("\u001b[0m"); // reset font ANSI
    }

    /**
     * Re-prints the user input to the stream in green.
     *
     * @param message String user input.
     */
    private void reprintUserInput(String message) {
        out.print("\u001b[32m"); // green font ANSI
        out.println("[Entered: " + message + " ]");
        out.println("\u001b[0m");
    }

    /**
     * Prints the formatted string description of all the tasks in the task list.
     *
     * @param taskList The taskList to print.
     */
    public void printAllTask(TaskList taskList) {
        printOutput(taskList.getCompleteList());
    }

    /**
     * Process the UI to show to the user based on the command entered.
     * Depends on the specific command, it will output the action done as a visual feedback
     * on successful operation.
     *
     * @param item Item description.
     * @param command Command queried.
     * @param size Size of the Task List.
     */
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
            default:
                break;
        }
    }

    /**
     * Print formatted output for any task addition action (Todo, Deadline, Event).
     *
     * @param item Item description.
     * @param size Size of the Task List after addition.
     * @param command Type of Command.
     */
    public void printAddActionResult(String item, int size, String command) {
        String output = "I got you, added a "
                + command + ":\n"+  item
                + "\nNow you have " + size + " tasks in the list.";
        printOutput(output);
    }

    /**
     * Print formatted output for delete action.
     *
     * @param item Item Description.
     * @param size Size of the Task List after deletion.
     */
    public void printDeleteActionResult(String item, int size) {
        String output = "Noted. I've removed this task:\n"
                + item
                + "\n Now you have " + size + " tasks in the list.";
        printOutput(output);
    }

    /**
     * Print formatted output for Mark and Unmark action.
     *
     * @param item Item description.
     * @param status Completed or not completed.
     */
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
