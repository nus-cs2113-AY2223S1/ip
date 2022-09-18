package duke.ui;

import duke.task.Task;
import duke.task.TaskList;

import java.util.Scanner;

public class Ui {

    private final static String logo2 = "                   _      \n"
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

    public String readCommand() {
        return in.nextLine();
    }

    public void printToUser(String line) {
        System.out.println(line + System.lineSeparator() + DIVIDER);
    }

    public void printGreeting() {
        printToUser("Hello I'm\n" + logo2 + System.lineSeparator() + "What you want?");
    }

    public void printBye() {
        printToUser("bye bye");
    }

    public void printAddTask(TaskList tasks) {
        int tasksLength = tasks.getSize();
        String taskType = tasks.findTask(tasksLength - 1).toString();
        printToUser("task added" + System.lineSeparator() + SPACER + taskType + System.lineSeparator()
                + "you still have " + tasksLength + " tasks left");
    }

    public void printDeleteTask(TaskList tasks, int taskNum) {
        int tasksLength = tasks.getSize();
        if (tasksLength != 0) {
            String taskType = tasks.findTask(taskNum).toString();
            printToUser("task deleted" + System.lineSeparator() + SPACER + taskType + System.lineSeparator()
                    + "you still have " + (tasksLength - 1) + " tasks left");
        }
    }

    public void printMarkTask(TaskList tasks, int taskNum) {
        String taskType = tasks.findTask(taskNum).toString();
        printToUser("that was fast\n" + SPACER + taskType);
    }

    public void printUnmarkTask(TaskList tasks, int taskNum) {
        String taskType = tasks.findTask(taskNum).toString();
        printToUser("can you make up your mind\n" + SPACER + taskType);
    }

    public void printError(String message) {
        printToUser(message);
    }

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
