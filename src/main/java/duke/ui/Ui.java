package duke.ui;

import java.util.Scanner;

import static duke.common.Messages.MESSAGE_FAREWELL;
import static duke.common.Messages.MESSAGE_GREETING;
import static duke.common.Messages.MESSAGE_INQUIRY;
import static duke.common.Messages.MESSAGE_ADD_TASK_ACKNOWLEDGEMENT;
import static duke.common.Messages.MESSAGE_DELETE_TASK_ACKNOWLEDGEMENT;
import static duke.common.Messages.MESSAGE_MARK_TASK_ACKNOWLEDGEMENT;
import static duke.common.Messages.MESSAGE_UNMARK_TASK_ACKNOWLEDGEMENT;
import static duke.common.Messages.MESSAGE_TASK_LISTING_HEADER;

/**
 * <code>Ui</code> is the class that represents the user interface of the program.
 * Operations in the Ui include reading user commands from the input
 * and displaying relevant application messages to the user in the output.
 */
public class Ui {
    private static final String DIVIDER = "____________________________________________________________";
    private static final String FORMATTED_MARK_LOGO = "\t[X] ";
    private static final String FORMATTED_UNMARK_LOGO = "\t[ ] ";


    private Scanner in;

    /**
     * Constructor of <code>Ui</code>. Creates a Scanner object to read user input.
     */
    public Ui() {
        this.in = new Scanner(System.in);
    }

    //@@author wcwy-reused
    //Reused from from https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/ui/TextUi.java
    // with minor modifications

    /**
     * Take in a variable number of String and print them out line by line
     *
     * @param messages A string Varargs
     */
    public void displayMessages(String... messages) {
        for (String message : messages) {
            System.out.println(message);
        }
    }
    //@@author

    /**
     * Read a line of user input.
     *
     * @return A string containing a line of user input.
     */
    public String readCommand() {
        String userInput = in.nextLine();
        return userInput;
    }

    /**
     * Display a greeting message to welcome the user.
     */
    public void displayGreetingMessage() {
        displayMessages(
                DIVIDER,
                MESSAGE_GREETING.toString(),
                MESSAGE_INQUIRY.toString(),
                DIVIDER
        );
    }

    /**
     * Display all the tasks in the task list to the user in a formatted output.
     *
     * @param listContent A string containing the formatted list of tasks information
     */
    public void displayListingMessage(String listContent) {
        displayMessages(
                DIVIDER,
                MESSAGE_TASK_LISTING_HEADER.toString(),
                listContent,
                DIVIDER
        );
    }

    /**
     * Display the information of new task added to the list to user on successful task addition.
     *
     * @param taskDetails Formatted information of the newly added task.
     * @param count       Number of tasks in the list after task addition.
     */
    public void displayTaskAdditionMessage(String taskDetails, int count) {
        displayMessages(
                DIVIDER,
                MESSAGE_ADD_TASK_ACKNOWLEDGEMENT.toString(),
                "\t" + taskDetails,
                "Now you have " + count + " tasks in the list",
                DIVIDER
        );
    }

    /**
     * Display the information of the deleted task to user on successful task removal.
     *
     * @param taskDetails Formatted information of the deleted task.
     * @param count       Number of tasks in the list after task removal.
     */
    public void displayTaskDeletionMessage(String taskDetails, int count) {
        displayMessages(
                DIVIDER,
                MESSAGE_DELETE_TASK_ACKNOWLEDGEMENT.toString(),
                "\t" + taskDetails,
                "Now you have " + count + " tasks in the list",
                DIVIDER
        );
    }

    /**
     * Display the information of the marked task to user on successful marK.
     *
     * @param taskName The description of the task marked.
     */
    public void displayTaskMarkedMessage(String taskName) {
        displayMessages(
                DIVIDER,
                MESSAGE_MARK_TASK_ACKNOWLEDGEMENT.toString(),
                FORMATTED_MARK_LOGO + taskName,
                DIVIDER
        );
    }

    /**
     * Display the information of the unmarked task to user on successful unmark.
     *
     * @param taskName The description of the task umarked.
     */
    public void displayTaskUnmarkedMessage(String taskName) {
        displayMessages(
                DIVIDER,
                MESSAGE_UNMARK_TASK_ACKNOWLEDGEMENT.toString(),
                FORMATTED_UNMARK_LOGO + taskName,
                DIVIDER
        );
    }

    /**
     * Display a farewell message to user on program exit.
     */
    public void displayExitMessage() {
        displayMessages(
                DIVIDER,
                MESSAGE_FAREWELL.toString(),
                DIVIDER
        );
    }

    /**
     * Display the error message returned on caught exceptions.
     *
     * @param exceptionMessage A string containing the error message.
     */
    public void displayErrorMessage(String exceptionMessage) {
        displayMessages(
                DIVIDER,
                exceptionMessage,
                DIVIDER
        );
    }
}
