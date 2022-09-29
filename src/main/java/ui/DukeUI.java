package ui;

import java.util.Scanner;
public class DukeUI {
    private static final String DIVIDER = "=================================================== \n";
    private static final String MESSAGE_WELCOME = "Duke: Hello! I'm Duke\n" +
            "Duke: What can I do for you?\n";
    private static final String MESSAGE_BYE = "Duke: Bye. Hope to see you again soon!";
    private static final String MESSAGE_ADD = "Duke: Got it. I've added this task: \n";
    public static final String MESSAGE_DELETE = "Duke: Noted. I have deleted the task below: \n";
    private static final String MESSAGE_CLEAR = "Duke: duke.txt has been cleared.";
    private static final String MESSAGE_LIST = "Duke: Here are the tasks for today: \n";
    private static final String MESSAGE_DONE = "Duke: The task below has been completed. Good job! \n";
    private static final String MESSAGE_UNDONE = "Duke: The task below has not been completed. Do it soon! \n";
    private static final String MESSAGE_FILE_SUCCESS = "The file has successfully been created!";
    private static final String MESSAGE_FILE_EXISTS = "The file already exists!";
    private static final String INDENT = "    ";
    private static final Scanner in = new Scanner(System.in);

    /**
     * Get the input from user
     *
     * @return return the input that has been typed in the terminal by user
     */
    public static String getInput() {
        System.out.print("User: ");
        return in.nextLine();
    }

    /**
     * Show the welcome message when Duke starts
     */
    public static void showWelcomeMessage() {
        System.out.print(MESSAGE_WELCOME + DIVIDER);
    }

    /**
     * Show the goodbye message when Duke terminates, when user inputs COMMAND_BYE
     */
    public void showByeMessage() {
        System.out.println(DIVIDER + MESSAGE_BYE);
    }

    /**
     * Show the message when the file duke.txt has successfully been created
     */
    public void showFileSuccessMessage() {
        System.out.println(MESSAGE_FILE_SUCCESS);
    }

    /**
     * show the message when the file duke.txt already exists and has been found by Duke.
     */
    public void showFileExistsMessage() {
        System.out.println(MESSAGE_FILE_EXISTS);
    }
    /**
     * Show the message when a valid task has been added
     *
     * @param description description of the task to be added
     */
    public static void addTaskMessage(String description) {
        System.out.println(MESSAGE_ADD + INDENT + description);
    }

    /**
     * Show the message when a  task has been deleted
     * @param description description of the task to be deleted
     */
    public void deleteTaskMessage(String description) {
        System.out.println(MESSAGE_DELETE + INDENT + description);
    }

    /**
     * Show the message when all the inputs have been cleared
     */
    public void clearTextFileMessage() {
        System.out.println(MESSAGE_CLEAR);
    }

    /**
     * Show the message when a task is to be marked as done or undone
     * @param description description of the task to be marked
     * @param isDone status of the task to be mared
     */
    public void markingMessage(String description, boolean isDone) {
        if (isDone) {
            System.out.println(MESSAGE_DONE + INDENT + description);
        } else {
            System.out.println(MESSAGE_UNDONE + INDENT + description);
        }
    }

    /**
     * show the message when user wants to see the list of tasks
     */
    public void listMessage() {
        System.out.print(MESSAGE_LIST);
    }
}
