package duke.ui;

import duke.task.TaskList;
import duke.task.Task;

import java.util.Scanner;

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

    public UI() {
        scanner = new Scanner(System.in);
    }

    private void reply(String message) {
        System.out.print(HORIZONTAL_DIVIDER + message + "\n" + HORIZONTAL_DIVIDER);
    }

    public void greet() {
        reply("Hello! I'm Banana\n"
                + "How can I help you?\n"
                + LOGO);
    }

    public void bye() {
        reply("Good bye. Hope to see you again soon!");
    }

    public void confirmAdd(Task task, TaskList taskList) {
        reply("Got it, I added this task to your list:\n"
                + ARROW + task
                + "\nNow you have " + taskList.getSize() + " task" + (taskList.getSize() == 1 ? "" : "s") + " in the list");
    }

    public void confirmMark(Task task) {
        reply("Congratulations! You have done this task:\n"
                + ARROW + task);
    }

    public void confirmUnmark(Task task) {
        reply("OK, I've marked this task as not done yet:\n"
                + ARROW + task);
    }

    public void confirmDelete(Task task, TaskList taskList) {
        reply("Noted. I've removed this task:\n"
                + ARROW + task
                + "\nNow you have " + taskList.getSize() + " task" + (taskList.getSize() == 1 ? "" : "s") + " in the list");
    }

    public void printList(TaskList taskList) {
        reply(taskList.toString());
    }

    public void printResultList(TaskList taskList) {
        reply(taskList.toStringFindResult());
    }

    public String getUserInput() {
        System.out.print("Enter command: ");
        return scanner.nextLine();
    }

    public void printError(String errorMessage) {
        reply(errorMessage);
    }
}
