package duke;

import duke.exceptions.UnrecognizedCommandException;

import java.util.Scanner;

/**
 * This class manages everything that has to do with accepting user input, as well as providing important helper functions for output.
 *
 * @author Dhanish
 */
public class Ui {

    public static final String DUKE_LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    public static final String DEADLINE_SEPERATOR_ERROR_MESSAGE = "Incorrect format! Deadline must contain a task and its deadline separated by '/by'! Refer to 'help' for more details!";
    public static final String MISSING_DEADLINE_DESCRIPTION_ERROR_MESSAGE = "Incorrect format! The description of a deadline cannot be empty!";
    public static final String MISSING_DEADLINE_TIME_ERROR_MESSAGE = "Incorrect format! The deadline of a deadline cannot be empty!";
    public static final String MISSING_EVENT_DESCRIPTION_ERROR_MESSAGE = "Incorrect format! The description of an event cannot be empty!";
    public static final String EVENT_SEPERATOR_ERROR_MESSAGE = "Incorrect format! Event must contain a task and its time separated by '/at'! Refer to 'help' for more details!";
    public static final String MISSING_EVENT_TIME_ERROR_MESSAGE = "Incorrect format! The time of an event cannot be empty!";
    public static final String MISSING_TODO_DESCRIPTION_ERROR_MESSAGE = "Incorrect format! The description of a todo cannot be empty!";
    public static final String MISSING_TASK_NUMBER_ERROR_MESSAGE = "Incorrect format! The task number that is to be marked as done/not done cannot be empty!";

    public static final String HELP_MESSAGE
            = "\tlist of valid commands" +
            "\n\t----------------------" +
            "\n\n\ttodo " +
            "\n\t----" +
            "\n\trecord a generic task without a deadline; format: 'todo' [name of task]" +
            "\n\n\tdeadline " +
            "\n\t--------" +
            "\n\trecord a task with a specific deadline; format: 'deadline' [name of task] '/by' [deadline]" +
            "\n\n\tevent " +
            "\n\t-----" +
            "\n\trecord a task happening at a specific time; format: 'event' [name of event] '/at' [time]" +
            "\n\n\tmark " +
            "\n\t----" +
            "\n\tmark a specific task as done; format: 'mark' [number of task]" +
            "\n\n\tunmark" +
            "\n\t------" +
            "\n\tmark a specific task as not done; format: 'unmark' [number of task]" +
            "\n\n\tdelete" +
            "\n\t------" +
            "\n\tdelete a specific task from the list of tasks; format: 'delete' [number of task]" +
            "\n\n\tlist" +
            "\n\t----" +
            " \n\tlist out all tasks and their completion status" +
            "\n\n\tbye" +
            "\n\t---" +
            " \n\texit the program";

    Scanner scanner;

    /**
     * Constructor that initializes the Scanner that will be used to read input from user throughout the program.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * This method is used to print error messages in a neat format.
     *
     * @param errorMessage - error message to be printed.
     */
    public static void showErrorMessage(String errorMessage) {
        printLine();
        System.out.println("\t " + errorMessage);
        printLine();
    }

    /**
     * This method first accepts user input through other helper functions.
     * It then processes it to retrieve the command keyword, and validates it. If the input is not valid, an exception is caught.
     * It repeates the process until a valid input is obtained.
     *
     * @return - the valid input obtained at the end of the method.
     */
    public String acceptAndValidateInput() {
        boolean isInputValid = false;
        String input = "";
        String command = "";
        while (!isInputValid) {
            try {
                input = readLine();
                command = Parser.retrieveCommand(input);
                Parser.validateCommand(command);
                isInputValid = true;
            } catch (UnrecognizedCommandException e) {
                printLine();
                System.out.println("\t" + e.getMessage());
                printLine();
            }
        }
        return input;
    }

    private String readLine() {
        return scanner.nextLine().trim();
    }

    /**
     * Prints a help message containing all the valid commands and their formats.
     */
    public void printHelpMessage() {
        System.out.println(HELP_MESSAGE);
    }

    /**
     * Useful helper method to print a solid underline.
     */
    public static void printLine() {
        System.out.print("\t");
        for (int i = 0; i < 60; i++) {
            System.out.print("\u2500");
        }
        System.out.println();
    }

    /**
     * Prints a welcome message to the user at the start of the program.
     */
    public void greet() {
        System.out.println("Hello from\n" + Ui.DUKE_LOGO);
        printLine();
        System.out.println("\tHello! I'm Duke!");
        System.out.println("\tWhat can I do for you?");
        printLine();
    }

}
