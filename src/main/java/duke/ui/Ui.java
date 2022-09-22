package duke.ui;

import duke.task.Task;
import duke.task.TaskList;

import java.util.Scanner;

/**
 * the Ui class which handles Duke's UI
 * print string to be displayed to user
 */
public class Ui {

    private final static String logo = "                   _      \n"
            + "                  | |     \n"
            + "  _   _ _ __   ___| | ___ \n"
            + " | | | | '_ \\ / __| |/ _ |\n"
            + " | |_| | | | | (__| |  __/ \n"
            + "  \\__,_|_| |_|\\___|_|\\___| \n";

    private static final String DIVIDER = "-------------------------------------------------";

    private static final String SPACER = "  ";

    private final Scanner in;

    public Ui() {
        this.in = new Scanner(System.in);
    }

    /**
     * read in user input
     *
     * @return user input in the form of a String
     */
    public String readCommand() {
        return in.nextLine();
    }

    /**
     * print message to user
     *
     * @param line message to be printed in the form of a String
     */
    public void printToUser(String line) {
        System.out.println(line + System.lineSeparator() + DIVIDER);
    }

    /**
     * prints greeting when program is started
     */
    public void printGreeting() {
        printToUser("Hello I'm\n" + logo + System.lineSeparator() + "What you want?");
    }

    /**
     * prints bye when program is closed
     */
    public void printBye() {
        printToUser("bye bye");
    }

    /**
     * print added task
     *
     * @param tasks TaskList which contains the added task
     */
    public void printAddTask(TaskList tasks) {
        int tasksLength = tasks.getSize();
        String taskType = tasks.findTask(tasksLength - 1).toString();
        printToUser("task added" + System.lineSeparator() + SPACER + taskType + System.lineSeparator()
                + "you still have " + tasksLength + " tasks left");
    }

    /**
     * print deleted task
     *
     * @param tasks   TaskList which contains task that was deleted
     * @param taskNum index of task to be deleted
     */
    public void printDeleteTask(TaskList tasks, int taskNum) {
        int tasksLength = tasks.getSize();
        if (tasksLength != 0) {
            String taskType = tasks.findTask(taskNum).toString();
            printToUser("task deleted" + System.lineSeparator() + SPACER + taskType + System.lineSeparator()
                    + "you still have " + (tasksLength - 1) + " tasks left");
        }
    }

    /**
     * print marked task
     *
     * @param tasks   TaskList containing the task to be marked
     * @param taskNum index of task to be marked
     */
    public void printMarkTask(TaskList tasks, int taskNum) {
        String taskType = tasks.findTask(taskNum).toString();
        printToUser("that was fast\n" + SPACER + taskType);
    }

    /**
     * print unmarked task
     *
     * @param tasks   TaskList containing the task to be unmarked
     * @param taskNum index of task to be unmarked
     */
    public void printUnmarkTask(TaskList tasks, int taskNum) {
        String taskType = tasks.findTask(taskNum).toString();
        printToUser("can you make up your mind\n" + SPACER + taskType);
    }

    /**
     * print error message
     *
     * @param message String to be printed
     */
    public void printError(String message) {
        printToUser(message);
    }

    /**
     * print the tasks found using find by keyword
     *
     * @param tasksFound TaskList containing tasks that were found
     */
    public void printTasksFound(TaskList tasksFound) {
        int counter = 1;
        System.out.println("Here are the tasks found:");
        for (int i = 0; i < tasksFound.getSize(); i += 1) {
            if (tasksFound.findTask(i) != null) {
                Task task = tasksFound.findTask(i);
                String toBePrinted = task.toString();
                System.out.println(counter + ". " + toBePrinted);
                counter++;
            }
        }
        System.out.println(DIVIDER);
    }

    /**
     * print a list of tasks
     *
     * @param tasks TaskList containing the tasks to be printed
     */
    public void printListOfTasks(TaskList tasks) {
        int counter = 1;
        System.out.println("come uncle show you your tasks");
        for (int i = 0; i < tasks.getSize(); i += 1) {
            if (tasks.findTask(i) != null) {
                Task task = tasks.findTask(i);
                String toBePrinted = task.toString();
                System.out.println(counter + ". " + toBePrinted);
                counter++;
            }
        }
        System.out.println(DIVIDER);
    }

}
