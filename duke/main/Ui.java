package duke.main;
import duke.error.DukeException;
import duke.tasks.*;
import org.w3c.dom.html.HTMLObjectElement;

import java.util.ArrayList;

public class Ui {
    static String HORIZONTAL_LINE = "------------------------------------------------------------";
    static String LINE_DIVIDER = "/";

    public Ui() {}

    public static void greet() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(HORIZONTAL_LINE);
    }

    public static void showLoadingError() {
        System.out.println("No previous usage of Duke");
        System.out.println("Loading a new save");
    }

    public static void showCommandError() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Oops! I don't understand that command!");
        System.out.println(HORIZONTAL_LINE);
    }

    public static void addTaskMessage(Task task) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + TaskList.getTaskCounter() + " tasks in the list.");
        System.out.println(HORIZONTAL_LINE);
    }
    public static void deleteTaskMessage(Task task) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        System.out.println(HORIZONTAL_LINE);
    }

    public static void markTaskMessage(Task task) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[" + task.getStatusIcon() + "] " + task.getName());
        System.out.println(HORIZONTAL_LINE);
    }

    public static void unmarkTaskMessage(Task task) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("[" + task.getStatusIcon() + "] " + task.getName());
        System.out.println(HORIZONTAL_LINE);
    }

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

    static boolean checkDivider(String line) throws DukeException {
        if (!line.contains(LINE_DIVIDER)) {
            throw new DukeException("Don't forget to add a date!");
        }
        return true;
    }

    public static void printTasks(ArrayList<Task> tasks) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Here are the tasks in your list:");
        int counter = 1;
        for (Task task : tasks) {
            System.out.println(counter + task.toString());
        }
        System.out.println(HORIZONTAL_LINE);
    }

    public static void exit() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(HORIZONTAL_LINE);
    }
}
