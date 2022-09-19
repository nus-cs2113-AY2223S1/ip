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
                MESSAGE_GREETING,
                MESSAGE_INQUIRY,
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
                MESSAGE_TASK_LISTING_HEADER,
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
                MESSAGE_ADD_TASK_ACKNOWLEDGEMENT,
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
                MESSAGE_DELETE_TASK_ACKNOWLEDGEMENT,
                "\t" + taskDetails,
                "Now you have " + count + " tasks in the list",
                DIVIDER
        );
    }

    /**
     * Display the information of the marked task to user on successful mark or unmark.
     *
     * @param taskName The description of the task marked.
     * @param isMark   Boolean value represent whether it is a mark operation or unmark operation.
     */
    public void displayMarkOrUnmarkMessage(String taskName, boolean isMark) {
        displayMessages(
                DIVIDER,
                getMessageAcknowledgementForMarkAndUnmark(isMark),
                getCheckboxForMarkAndUnmark(isMark) + taskName,
                DIVIDER
        );
    }

    /**
     * Display a farewell message to user on program exit.
     */
    public void displayExitMessage() {
        displayMessages(
                DIVIDER,
                MESSAGE_FAREWELL,
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

    /**
     * Return a mark acknowledgement or unmark acknowledgement correspondingly.
     *
     * @param isMark A boolean value to indicate whether it is a mark or unmark option
     * @return A string containing the acknowledgement message
     */
    private static String getMessageAcknowledgementForMarkAndUnmark(boolean isMark) {
        String output = "";
        if (isMark) {
            output = MESSAGE_MARK_TASK_ACKNOWLEDGEMENT;
        } else {
            output = MESSAGE_UNMARK_TASK_ACKNOWLEDGEMENT;
        }
        return output;
    }

    /**
     * Return a marked checkbox or unmarked checkbox correspondingly.
     *
     * @param isMark A boolean value to indicate whether it is a mark or unmark option
     * @return A string containing the marking checkbox
     */
    private static String getCheckboxForMarkAndUnmark(boolean isMark) {
        String output = "";
        if (isMark) {
            output = "\t[X] ";
        } else {
            output = "\t[ ] ";
        }
        return output;
    }
}
