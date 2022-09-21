package duke;

import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    public static final String DIVIDER_LINE = "----------------------------------------------------";
    public static final String logo = " ____        _\n"
            + "|  _ \\ _   _| | _____\n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    public static final String helpGuide = "Here are some commands to start off:\n"
            + "  todo ____              to create a todo task\n"
            + "  deadline ____ /by ___  to create a deadline task\n"
            + "  event ____ /at ___     to create an event task\n"
            + "  list                   to list all tasks\n"
            + "  delete __              to delete a task\n"
            + "  mark __                to mark a task\n"
            + "  unmark __              to unmark a task\n"
            + "  bye                    to end the Duke Application\n";

    private Scanner in;
    private String input;

    /**
     * Constructor of Ui Class
     */
    public Ui() {
        in = new Scanner(System.in);
    }

    /**
     * Prints the intro message
     */
    public void showWelcome() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println(helpGuide);

    }

    /**
     * Returns User's string input
     *
     * @return Trimmed string of user input
     */
    public String readCommand() {
        input = in.nextLine();
        return input.trim();
    }

    /**
     * Prints divider line
     */
    public void showLine() {
        System.out.println(DIVIDER_LINE);
    }

    /**
     * Prints message when Task is added to Tasks
     *
     * @param tasks Arraylist of Tasks
     * @param t The Task added to Tasks
     */
    public void addTaskMessage(ArrayList<Task> tasks, Task t) {
        System.out.println("Got it. I've added this task: \n  " + t);
        System.out.println("Now you have " + tasks.size() + " tasks in the list\n");
    }

    /**
     * Prints message when Task is deleted from Tasks
     *
     * @param tasks Arraylist of Tasks
     * @param taskInt the index of Task to be removed
     */
    public void deleteTaskMessage(ArrayList<Task> tasks, int taskInt) {
        System.out.println("Noted. I've removed this task: \n  " + tasks.get(taskInt));
        Integer remainingTasks = tasks.size() - 1;
        System.out.println("Now you have " + remainingTasks + " tasks in the list\n");
    }

    /**
     * Lists out all the tasks in Tasks
     *
     * @param tasks Tasklist of tasks
     */
    public void listTasks(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.println(i + 1 + ". " + tasks.getTask(i));
        }
        System.out.println();
    }

    /**
     * Prints Message when a Task is marked as done
     * @param tasks Arraylist of Tasks
     * @param markedNum Index of Task to be marked
     */
    public void markTaskMessage(ArrayList<Task> tasks, int markedNum) {
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("  " + tasks.get(markedNum) + "\n");
    }

    /**
     * Prints Message when a Task is unmarked
     *
     * @param tasks Arraylist of Tasks
     * @param unmarkedNum Index of Task that needs to be unmarked
     */
    public void unmarkTaskMessage(ArrayList<Task> tasks, int unmarkedNum) {
        System.out.println("Oh no :( I've marked it as uncompleted: ");
        System.out.println("  " + tasks.get(unmarkedNum) + "\n");
    }

    /**
     * Prints Error Message of Duke exception
     *
     * @param errorMessage Error Message from Exception
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * Prints the Tasks that matched with the searchPhrase
     *
     * @param tasks Arraylist of Tasks
     * @param searchPhrase Singular word used to search each task
     */
    public void printMatchedTasks(ArrayList<Task> tasks, String searchPhrase) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            String taskDescription = tasks.get(i).getDescription();
            boolean isMatch = taskDescription.indexOf(searchPhrase) !=-1? true: false;
            if (isMatch) {
                System.out.println(i + 1 + ". " + tasks.get(i));
            }
        }
        System.out.println();
    }

    /**
     * Prints the Goodbye Message when Duke is terminated.
     *
     */
    public void goodbyeMessage() {
        System.out.println("Bye good friend! Hope to see you again soon!\n");
    }



}
