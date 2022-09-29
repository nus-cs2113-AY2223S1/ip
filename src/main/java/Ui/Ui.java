package Ui;

import Tasks.TaskList;

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
    private static final String MESSAGE_EMPTY_MARK_UNMARK_ARGS = "OOPS!!! Please input a number to mark/unmark as done.";
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

    public void printToUser(String message) {
        printOutput(message);
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

    public void printEmptyMarkUnmarkArgs() {
        printError(MESSAGE_EMPTY_MARK_UNMARK_ARGS);
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
