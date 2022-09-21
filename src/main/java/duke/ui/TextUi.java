package duke.ui;

import duke.common.InfoMessages;

import java.util.Scanner;

/**
 * Provides reading of user input and printing of
 * customised messages and program outputs to the user.
 */
public class TextUi {
    private static final String INDENT_SPACE = "    ";
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private Scanner in;

    /**
     * Shows welcome text and provides input stream when user initialises a new instance of this class.
     */
    public TextUi() {
        showWelcomeText();
        in = new Scanner(System.in);
    }

    /**
     * Prints each message from a variable messages string line by line into the output stream.
     *
     * @param messages A string of variable arguments.
     */
    public void printMessages(String... messages) {
        //@@author chydarren-reused
        // Reused from https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/ui/TextUi.java
        // with minor modifications
        System.out.println(INDENT_SPACE + InfoMessages.MESSAGE_INFO_DIVIDER.toString());
        // Prints the string of arguments line by line in a loop.
        for (String message : messages) {
            System.out.println(INDENT_SPACE + message);
        }
        System.out.println(INDENT_SPACE + InfoMessages.MESSAGE_INFO_DIVIDER.toString());
    }

    /**
     * Reads a line of user input from the user via the input stream.
     *
     * @return A line of input entered by the user.
     */
    public String getUserInput() {
        String input = in.nextLine();
        return input;
    }

    /**
     * Shows a welcome text to the user.
     */
    public void showWelcomeText() {
        // Prints the messages by calling the printMessages function
        printMessages("*******     *******   *******       **" + LINE_SEPARATOR
                        + "    /**////**   **/////** /**////**     ****" + LINE_SEPARATOR
                        + "    /**    /** **     //**/**   /**    **//**" + LINE_SEPARATOR
                        + "    /**    /**/**      /**/*******    **  //**" + LINE_SEPARATOR
                        + "    /**    /**/**      /**/**///**   **********" + LINE_SEPARATOR
                        + "    /**    ** //**     ** /**  //** /**//////**" + LINE_SEPARATOR
                        + "    /*******   //*******  /**   //**/**     /**" + LINE_SEPARATOR
                        + "    ///////     ///////   //     // //      //",
                InfoMessages.MESSAGE_INFO_WELCOME.toString()
        );
    }

    /**
     * Shows custom program information or error messages to the user.
     *
     * @param message An enum variable that allows the parsing in of the custom information or error message.
     */
    public void showCustomText(Enum message) {
        // Prints the messages by calling the printMessages function
        printMessages(
                message.toString()
        );
    }

    /**
     * Shows a task list stored in the program.
     *
     * @param tasksList A string containing the formatted task list.
     * @param message An enum variable that allows the parsing in of the custom information message.
     */
    public void showTaskList(String tasksList, Enum message) {
        // Prints the messages by calling the printMessages function
        printMessages(
                message.toString(),
                tasksList
        );
    }

    /**
     * Shows information for the task that has been added into the program.
     *
     * @param taskDetails A string containing the information for the task that has been added.
     * @param taskSize An integer that states the current number of tasks in the task list.
     */
    public void showAddTaskInfo(String taskDetails, int taskSize) {
        // Prints the messages by calling the printMessages function
        printMessages(
                InfoMessages.MESSAGE_INFO_TASK_ADDED.toString(),
                taskDetails,
                // Formats the information message dynamically based on the given taskSize.
                String.format("%s", String.format(InfoMessages.MESSAGE_INFO_TASK_COUNT.toString(), taskSize))
        );
    }

    /**
     * Shows information for the task that has been marked or unmarked in the program.
     *
     * @param taskDetails A string containing the information for the task that has been marked or unmarked.
     * @param isDone A boolean that indicates whether the task is done or undone.
     */
    public void showMarkTaskInfo(String taskDetails, boolean isDone) {
        // Gets the information message dynamically based on the task completion status indicated in isDone.
        Enum message = (isDone ? InfoMessages.MESSAGE_INFO_TASK_MARKED : InfoMessages.MESSAGE_INFO_TASK_UNMARKED);
        // Prints the messages by calling the printMessages function
        printMessages(
                message.toString(),
                taskDetails
        );
    }

    /**
     * Shows information for the task that has been deleted in the program.
     *
     * @param taskDetails A string containing the information for the task that has been deleted.
     * @param taskCount An integer that states the current number of tasks in the task list.
     */
    public void showDeleteTaskInfo(String taskDetails, int taskCount) {
        // Prints the messages by calling the printMessages function
        printMessages(
                InfoMessages.MESSAGE_INFO_TASK_DELETED.toString(),
                taskDetails,
                // Formats the information message dynamically based on the given taskCount.
                String.format("%s", String.format(InfoMessages.MESSAGE_INFO_TASK_COUNT.toString(), taskCount))
        );
    }
}

