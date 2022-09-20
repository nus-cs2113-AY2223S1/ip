package duke;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * The Ui class handles reading input and displaying output.
 */
public class Ui {
    private static final String INDENT = "    ";
    private static final String HORIZONTAL_RULE = "____________________________________________________________\n";
    private static final String DUKE_LOGO = String.join("\n", " ____        _        ", "|  _ \\ _   _| | _____ ",
            "| | | | | | | |/ / _ \\", "| |_| | |_| |   <  __/", "|____/ \\__,_|_|\\_\\___|", "");
    private static final String[] GREETING = { "Hello! I'm Duke", "What can I do for you?" };
    private static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";
    private final Scanner scanner;

    /**
     * Initializes the Ui object.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Displays the results of a command, indented.
     * 
     * @param lines The results to be shown.
     */
    public void displayMessage(String[] lines) {
        System.out.println(INDENT + HORIZONTAL_RULE);
        for (String line : lines) {
            System.out.println(INDENT + " " + line);
        }
        System.out.println(INDENT + HORIZONTAL_RULE);
    }

    /**
     * Displays the results of a command, indented.
     * 
     * @param line The results to be shown.
     */
    public void displayMessage(String line) {
        displayMessage(line.split("\n"));
    }

    /**
     * Displays the greeting message, to be called when the application starts.
     */
    public void initialize() {
        System.out.println(DUKE_LOGO);
        displayMessage(GREETING);
    }

    /**
     * Reads a line of input from the user.
     * 
     * @return A line of user input
     */
    public String getInput() {
        try {
            return scanner.nextLine();
        } catch (NoSuchElementException e) {
            return "bye";
        }
    }

    /**
     * Displays the exit message, to be called right before the application exits.
     */
    public void cleanUp() {
        displayMessage(EXIT_MESSAGE);
        scanner.close();
    }
}
