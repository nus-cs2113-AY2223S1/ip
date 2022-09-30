package duke;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Ui represents the user interface through which the user interacts with Duke and vice versa.
 */
public class Ui {
    private Scanner in;
    private static final String LINE_BREAK = "______________________________________________\n";
    public Ui() {
        in = new Scanner(System.in);
    }
    public String nextInputLine() {
        return in.nextLine();
    }
    public void showWelcome() {
        System.out.println(LINE_BREAK + " Hello! I'm Duke\n" + " What can I do for you?\n" + LINE_BREAK);
    }
    public void showExit() {
        System.out.println(LINE_BREAK + "Bye. Hope to see see you again soon!\n" + LINE_BREAK);
    }

    /**
     * Prints the tasks in the ArrayList of tasks in String format.
     *
     * @param tasks ArrayList of tasks
     */
    public void showTaskList(ArrayList<Task> tasks) {
        System.out.println(LINE_BREAK);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(Integer.toString(i + 1) + "." + tasks.get(i).toString());
        }
        System.out.println(LINE_BREAK);
    }

    /**
     * Prints the tasks found using keywords provided by the user.
     *
     * @param tasks ArrayList of tasks
     */
    public void showFoundTasks(ArrayList<Task> tasks) {
        System.out.println(LINE_BREAK);
        System.out.println("Here are the matching tasks in your list: ");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(Integer.toString(i + 1) + "." + tasks.get(i).toString());
        }
        System.out.println(LINE_BREAK);
    }
    public void showInvalidCommand() {
        System.out.println(LINE_BREAK + '\n' + "Oh no! I do not understand the command!\n" + LINE_BREAK);
    }

    /**
     * Prints that the markedTask has been marked as completed.
     *
     * @param markedTask Task which is marked as completed.
     */
    public void showMarkedTask(Task markedTask) {
        System.out.println(LINE_BREAK + "Nice! I've marked this task as done: ");
        System.out.println("  " + markedTask.toString() + '\n' + LINE_BREAK);
    }

    /**
     * Prints that the unmarkedTask has been marked as incomplete.
     *
     * @param unmarkedTask Task which is marked as incomplete.
     */
    public void showUnmarkedTask(Task unmarkedTask) {
        System.out.println(LINE_BREAK + "OK, I've marked this task as not done yet: ");
        System.out.println("  " + unmarkedTask.toString() + '\n' + LINE_BREAK);
    }

    /**
     * Prints the added task and shows the number of tasks left in the task list.
     *
     * @param numberOfTasks number of tasks that are currently in the task list.
     * @param task most recently added task
     */
    public void showAddedTask(Task task, int numberOfTasks) {
        System.out.println(LINE_BREAK + "Got it. I've added this task: ");
        System.out.println(" " + task.toString());
        System.out.println("Now you have " + Integer.toString(numberOfTasks) + " tasks in the list \n" + LINE_BREAK);
    }

    /**
     * Prints the deleted task and shows the number of tasks left in the task list.
     *
     * @param numberOfTasks number of tasks that are currently in the task list.
     * @param deletedTask most recently deleted task
     */
    public void showDeletedTask(Task deletedTask, int numberOfTasks) {
        System.out.println(LINE_BREAK + "Noted. I've removed this task: ");
        System.out.println("  " + deletedTask.toString());
        String numberOfTasksAfterDeletion = Integer.toString(numberOfTasks - 1);
        System.out.println("Now you have " + numberOfTasksAfterDeletion + " tasks in the list \n" + LINE_BREAK);
    }
    public void printEmptyEventDescription() {
        System.out.println(LINE_BREAK + "Oh No! The description of an event cannot be empty.\n" + LINE_BREAK);
    }
    public void printEmptyDeadlineDescription() {
        System.out.println(LINE_BREAK + "Oh No! The description of a deadline cannot be empty.\n" + LINE_BREAK);
    }
    public void printInvalidDeadline() {
        System.out.println(LINE_BREAK + "Invalid Deadline. Please provide due date!\n" + LINE_BREAK);
    }

    public void printInvalidEvent() {
        System.out.println(LINE_BREAK + "Invalid Event. Please provide event time!\n" + LINE_BREAK);
    }
    public void printInvalidToDo() {
        System.out.println(LINE_BREAK + "Oh No! The description of a todo cannot be empty.\n" + LINE_BREAK);
    }
    public static void showMessage(String Message) {
        System.out.println(Message);
    }
    public static void showException(Exception e) {
        System.out.println(e.getMessage());
    }
}
