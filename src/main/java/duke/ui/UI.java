package duke.ui;

import duke.task.TaskList;
import duke.task.Task;

import java.util.Scanner;

/**
 * The user interface interacting with user.
 */
public class UI {
    public static final String HORIZONTAL_DIVIDER = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";
    public static final String ARROW = "----->  ";
    private static final String LOGO = "  ____                                 \n"
            + " |  _ \\                                \n"
            + " | |_) | __ _ _ __   __ _ _ __   __ _  \n"
            + " |  _ < / _` | '_ \\ / _` | '_ \\ / _` | \n"
            + " | |_) | (_| | | | | (_| | | | | (_| | \n"
            + " |____/ \\__,_|_| |_|\\__,_|_| |_|\\__,_| ";

    private final Scanner scanner;

    /**
     * Constructor of UI.
     */
    public UI() {
        scanner = new Scanner(System.in);
    }

    /**
     * Generates the reply message.
     *
     * @param message The main message.
     */
    private void reply(String message) {
        System.out.print(HORIZONTAL_DIVIDER + message + "\n" + HORIZONTAL_DIVIDER);
    }

    /**
     * Prints the greeting message.
     */
    public void printGreetingMessage() {
        reply("Hello! I'm Banana\n"
                + "How can I help you?\n"
                + LOGO);
    }
    /**
     * Prints the exit message when exiting Duke.
     */
    public void printExitMessage() {
        reply("Good bye. Hope to see you again soon!");
    }

    /**
     * Prints the confirmation after adding a new task.
     *
     * @param task The new task.
     * @param taskList The TaskList to be worked with.
     */
    public void confirmAdd(Task task, TaskList taskList) {
        reply("Got it, I added this task to your list:\n"
                + ARROW + task
                + "\nNow you have " + taskList.getSize() + " task(s) in the list");
    }

    /**
     * Prints the confirmation after marking a task as done.
     *
     * @param task The task marked as done.
     */
    public void confirmMark(Task task) {
        reply("Congratulations! You have done this task:\n"
                + ARROW + task);
    }
    /**
     * Prints the confirmation after marking a task as undone.
     *
     * @param task The task marked as undone.
     */
    public void confirmUnmark(Task task) {
        reply("OK, I've marked this task as not done yet:\n"
                + ARROW + task);
    }

    /**
     * Prints the confirmation after deleting a task.
     *
     * @param task The deleted task.
     * @param taskList The TaskList to be worked with.
     */
    public void confirmDelete(Task task, TaskList taskList) {

        reply("Noted. I've removed this task:\n"
                + ARROW + task
                + "\nNow you have " + taskList.getSize() + " task(s) in the list");
    }

    /**
     * Prints the list of tasks.
     *
     * @param taskList The list of tasks to be printed.
     */
    public void printList(TaskList taskList) {
        reply(taskList.toString());
    }

    /**
     * Prints the result list of matching tasks after finding.
     *
     * @param taskList The result list of tasks to be printed.
     */
    public void printResultList(TaskList taskList) {
        reply(taskList.toStringFindResult());
    }

    /**
     * Gets the user input.
     *
     * @return An input string from user.
     */
    public String getUserInput() {
        System.out.print("Enter command: ");
        return scanner.nextLine();
    }

    /**
     * Prints the error when there is an exception.
     *
     * @param errorMessage The error message.
     */
    public void printError(String errorMessage) {
        reply(errorMessage);
    }
}
