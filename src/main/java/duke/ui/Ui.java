package duke.ui;

import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the user interface that the user observes when using the program.
 */
public class Ui {
    static final String LOGO = "   _____                .__   \n" +
            "  /  _  \\ ___  ___ ____ |  |  \n" +
            " /  /_\\  \\\\  \\/  // __ \\|  |  \n" +
            "/    |    \\>    <\\  ___/|  |__\n" +
            "\\____|__  /__/\\_ \\\\___  >____/\n";

    /**
     * Greets the user with an introductory message when the program is first started.
     */
    public static void greetUser() {
        System.out.println(LOGO);
        String greeting = "____________________________________________________________\n"
                + "Hello! My name is Axel. :-)\n"
                + "How may I help you today?\n"
                + "____________________________________________________________";
        System.out.println(greeting);
    }

    /**
     * Bids goodbye to the user when the user decides to terminate the program.
     */
    public static void sayByeToUser() {
        String message = "____________________________________________________________\n"
                + "Goodbye. Hope to see you again soon!\n"
                + "____________________________________________________________";
        System.out.println(message);
    }

    /**
     * Prints an error message to inform the user of an error in loading their file.
     *
     * @param message The message provided by the getMessage() function for the exception that is used in conjunction with this function.
     */
    public static void showLoadingError(String message) {
        System.out.println("Something went wrong trying to load the file: " + message);
    }

    /**
     * Reads the line of input that the user entered to the program.
     *
     * @return The full user input to the program.
     */
    public static String getUserCommand() {
        Scanner input = new Scanner(System.in);
        String command = input.nextLine();
        return command;
    }

    /**
     * Prints a well-formatted list of the user's current tasks, including its type, description and done status.
     *
     * @param taskList The user's current list of tasks.
     */
    public void printTasks(TaskList taskList) {
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < taskList.getTasksCount(); i++){
            System.out.println((i+1) + "." + taskList.getTasks().get(i).toString());
        }
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints a well-formatted list of the user's tasks that matched a keyword provided by the user.
     *
     * @param taskList The user's current list of tasks.
     */
    public void printMatchingTasks(TaskList taskList) {
        System.out.println("____________________________________________________________");
        System.out.println("Here are the matching tasks in your list:");
        for(int i = 0; i < taskList.getTasksCount(); i++){
            System.out.println((i+1) + "." + taskList.getTasks().get(i).toString());
        }
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints an error message to inform the user that they tried to index a task that is out of bounds.
     */
    public static void printAccessTaskOutOfBoundsError() {
        String error = "    ____________________________________________________________\n" +
                "     ☹ OOPS!!! The task you have specified is out of bounds :-(\n";
        System.out.println(error);
    }

    /**
     * Prints an error message to inform the user that they did not provide a task description.
     */
    public void printEmptyDescriptionError() {
        String error = "    ____________________________________________________________\n" +
                "     ☹ OOPS!!! The description cannot be empty.\n" +
                "    ____________________________________________________________";
        System.out.println(error);
    }

    /**
     * Prints an error message to inform the user that they did not provide a task number.
     */
    public void printMissingTaskNumberError() {
        String error = "    ____________________________________________________________\n" +
                "     ☹ OOPS!!! You did not specify the task number :-(\n" +
                "    ____________________________________________________________";
        System.out.println(error);
    }

    /**
     * Prints an error message to inform the user that they are attempting to mark a task that is already done.
     */
    public static void printTaskAlreadyMarkedError() {
        String error = "    ____________________________________________________________\n" +
                "     ☹ OOPS!!! The task you specified is already marked!\n" +
                "    ____________________________________________________________";
        System.out.println(error);
    }

    /**
     * Prints an error message to inform the user that they are attempting to unmark a task that is already not done.
     */
    public static void printTaskAlreadyUnmarkedError() {
        String error = "    ____________________________________________________________\n" +
                "     ☹ OOPS!!! The task you specified is already unmarked!\n" +
                "    ____________________________________________________________";
        System.out.println(error);
    }

    /**
     * Prints an error message to inform the user that they did not provide a valid command to the program.
     */
    public void printUnknownCommandError() {
        String error = "    ____________________________________________________________\n" +
                "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                "    ____________________________________________________________";
        System.out.println(error);
    }

    /**
     * Prints an acknowledgement message to inform the user that they have successfully added a task to their list.
     *
     * @param tasks The ArrayList of the user's current tasks.
     * @param tasksCount The number of tasks in the user's current list.
     */
    public static void printAddTaskAcknowledgement(ArrayList<Task> tasks, int tasksCount) {
        String acknowledgement = "____________________________________________________________\n"
                + "Got it. I've added this task:\n"
                + " " + tasks.get(tasksCount).toString();
        System.out.println(acknowledgement);
        tasksCount++;
        System.out.println("Now you have " + tasksCount + " task(s) in the list.");
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints an acknowledgement message to inform the user that they have successfully deleted a task from their list.
     *
     * @param taskIndex The index of the task to delete.
     * @param tasks The ArrayList of the user's current tasks.
     * @param tasksCount The number of tasks in the user's current list.
     */
    public static void printDeleteTaskAcknowledgement(int taskIndex, ArrayList<Task> tasks, int tasksCount) {
        String acknowledgement = "____________________________________________________________\n"
                + "Noted. I've removed this task:\n"
                + " " + tasks.get(taskIndex).toString();
        System.out.println(acknowledgement);
        tasksCount--;
        System.out.println("Now you have " + tasksCount + " task(s) in the list.");
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints the number of tasks that the user currently has in their list.
     *
     * @param tasksCount The number of tasks in the user's current list.
     */
    public static void printNumberOfTasks(int tasksCount) {
        System.out.println("You currently have " + tasksCount + " task(s) in your list.\n"
                + "    ____________________________________________________________");
    }

    /**
     * Prints an acknowledgement message to inform the user that they have successfully marked a task from their list as done.
     *
     * @param taskIndex The index of the task to mark as done.
     * @param tasks The ArrayList of the user's current tasks.
     */
    public static void printMarkedTaskAsDoneAcknowledgement(int taskIndex, ArrayList<Task> tasks) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(taskIndex).toString());
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints an acknowledgement message to inform the user that they have successfully marked a task from their list as undone.
     *
     * @param taskIndex The index of the task to mark as undone.
     * @param tasks The ArrayList of the user's current tasks.
     */
    public static void printMarkedTaskAsUndoneAcknowledgement(int taskIndex, ArrayList<Task> tasks) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tasks.get(taskIndex).toString());
        System.out.println("____________________________________________________________");
    }
}
