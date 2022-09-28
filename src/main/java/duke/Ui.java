package duke;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Serves as the UI of the application.
 */
public class Ui {
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String MESSAGE_WELCOME = "Hello! I'm Duke\nWhat can I do for you?";

    private static final String MESSAGE_DEFAULT_TASK_RESPONSE = "Got it. I've added this task:\n%s " +
            "\nNow you have %d tasks in the list.";

    private final Scanner in;

    public Ui() {
        this.in = new Scanner(System.in);
    }

    /**
     * Prints the welcome message.
     */
    public void printWelcomeMessage() {
        System.out.println(LOGO + MESSAGE_WELCOME);
    }

    /**
     * Prints the message to inform the user that the specific task has been added.
     *
     * @param number the labelled number of the task in the list.
     * @param tasks the list of tasks containing the task that has been added.
     */
    public void printDefaultTaskResponseMessage(int number, ArrayList<Task> tasks) {
        System.out.printf(MESSAGE_DEFAULT_TASK_RESPONSE, tasks.get(number - 1).toString(), tasks.size());
    }

    /**
     * Prints the message to say goodbye to the user.
     */
    public void printGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints the list of tasks labelled with its number.
     *
     * @param tasks the list of tasks to be printed out.
     */
    public void printTaskList(ArrayList<Task> tasks) {
        System.out.println("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + "." + tasks.get(i).toString());
        }
    }

    /**
     * Prints a message to inform the user that an error has occurred while loading the file.
     */
    public void printLoadingError() {
        System.out.println("Error: Could not load file");
    }

    /**
     * Prints a message to inform the user that a task command must be followed by description.
     */
    public void printEmptyTaskDescriptionMessage() {
        System.out.println("Uh oh, the description of a task cannot be empty. Try again.");
    }

    /**
     * Prints a message to inform the user that the input is not a valid command.
     */
    public void printInvalidCommandMessage() {
        System.out.println("You have entered an invalid command, please try again.");
    }

    /**
     * Reads the user input and returns it as a String.
     *
     * @return fullInputLine a String which contains the full user input.
     */
    public String getUserCommand() {
        String fullInputLine = in.nextLine();
        return fullInputLine;
    }

    /**
     * Prints a message to inform the user that the program is closing.
     */
    public void printExitingMessage() {
        System.out.println("Exiting the program now.");
    }

    /**
     * Prints a message to inform the user that the task has been marked.
     */
    public void printTaskMarkedMessage() {
        System.out.println("Nice! I've marked this task as done:\n");
    }

    /**
     * Prints a message to inform the user that an error has occurred
     * while trying to mark the task.
     */
    public void printMarkingTaskErrorMessage() {
        System.out.println("Error: Could not mark task.");
    }

    /**
     * Prints a message to inform the user that the task has been unmarked.
     */
    public void printUnmarkTaskMessage() {
        System.out.println("OK, I've marked this task as not done yet:\n");
    }

    /**
     * Prints a message to inform the user that an error has occurred
     * while trying to unmark the task.
     */
    public void printUnmarkTaskErrorMessage() {
        System.out.println("Error: Could not unmark task.");
    }

    /**
     * Prints a message to inform the user that an error has occurred
     * while trying to update the file with the new data.
     */
    public void printFileUpdatingErrorMessage() {
        System.out.println("Error: Could not unmark task.");
    }

    /**
     * Prints a message to inform the user that the specified task has been deleted successfully.
     *
     * @param tasks the list of tasks to remove the specified task from.
     * @param taskToDelete the specified task to be removed.
     */
    public void printDeleteTaskMessage(ArrayList<Task> tasks, int taskToDelete) {
        System.out.printf("Noted. I've removed this task:\n%s\nNow you have %d tasks in the list."
                , tasks.get(taskToDelete - 1).toString(), tasks.size() - 1);

    }

    /**
     * Prints a message to inform the user that the task number given is invalid.
     */
    public void printInvalidTaskMessage() {
        System.out.println("This task number is invalid, try again.");
    }

    /**
     * Prints a message to inform the user that an error has occurred
     * while trying to delete the task.
     */
    public void printDeletingTaskErrorMessage() {
        System.out.println("Error: Task could not be deleted.");
    }

    /**
     * Prints the list of tasks that have been filtered based on what the user wants to find.
     *
     * @param filteredTasks the list of tasks that have been filtered based on the user's find criteria.
     */
    public void printFilteredTaskList(ArrayList<Task> filteredTasks) {
        System.out.println("Here are the matching tasks in your list:\n");
        for (int i = 0; i < filteredTasks.size(); i++) {
            System.out.println(i + 1 + "." + filteredTasks.get(i).toString());
        }
    }
}
