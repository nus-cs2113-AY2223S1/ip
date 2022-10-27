package duke.main;
import duke.error.DukeException;
import duke.tasks.*;

import java.util.ArrayList;

public class Ui {
    static String HORIZONTAL_LINE = "------------------------------------------------------------";
    static String LINE_DIVIDER = "/";

    public Ui() {}

    /**
     * Prints a greeting for the user
     */
    public static void greet() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints a message for the user if user has not used Duke before
     */
    public static void showLoadingError() {
        System.out.println("No previous usage of Duke");
        System.out.println("Loading a new save");
    }

    /**
     * Prints a message if the user uses a command that Duke does not understand
     */
    public static void showCommandError() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Oops! I don't understand that command!");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints a message to notify user that a task has been added
     * @param task task to be added to Duke
     */
    public static void addTaskMessage(Task task) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + TaskList.getTaskCounter() + " tasks in the list.");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints a message to notify the user that a task has been deleted
     * @param task task to be deleted by Duke
     */
    public static void deleteTaskMessage(Task task) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints a message to notify the user that a task has been marked as done
     * @param task task to be marked as done by Duke
     */
    public static void markTaskMessage(Task task) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString());
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints a message to notify the user that a task has been marked as undone
     * @param task task to be marked as undone by Duke
     */
    public static void unmarkTaskMessage(Task task) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task.toString());
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Checks if the user is missing the task name when inputting a task
     * @param line line of input from the user
     * @return true if there is a task name
     */
    static boolean checkSpace(String line) {
        // in the case of "todo"
        if (!line.contains(" ")) {
            throw new IllegalArgumentException("No task name included");
        }

        // in the case of "todo "
        int spaceLocation = line.indexOf(" ");
        if (spaceLocation == line.length()) {
            throw new IllegalArgumentException("No task name included");
        }

        return true;
    }

    /**
     * Checks if the user is missing a task date for event and deadline tasks
     * @param line line of input from the user
     * @return true if task date is not missing
     * @throws DukeException if there is no deadline
     */
    static boolean checkDivider(String line) throws DukeException {
        if (!line.contains(LINE_DIVIDER)) {
            throw new DukeException("Don't forget to add a date!");
        }
        return true;
    }

    /**
     * Prints all the tasks inside a tasks array
     * @param tasks array of tasks to be printed
     */
    public static void printTasks(ArrayList<Task> tasks) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Here are the tasks in your list:");
        int counter = 1;
        for (Task task : tasks) {
            System.out.println(counter + ". " + task.toString());
            counter += 1;
        }
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints a message to say goodbye to the user if they intend to exit Duke
     */
    public static void exit() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(HORIZONTAL_LINE);
    }
}
