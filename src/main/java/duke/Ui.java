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

    public Ui() {
        in = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println(helpGuide);

    }

    public String readCommand() {
        input = in.nextLine();
        return input.trim();
    }

    public void showLine() {
        System.out.println(DIVIDER_LINE);
    }

    public void addTaskMessage(ArrayList<Task> tasks, Task t) {
        System.out.println("Got it. I've added this task: \n  " + t);
        System.out.println("Now you have " + tasks.size() + " tasks in the list\n");
    }

    public void deleteTaskMessage(ArrayList<Task> tasks, int taskInt) {
        System.out.println("Noted. I've removed this task: \n  " + tasks.get(taskInt));
        Integer remainingTasks = tasks.size() - 1;
        System.out.println("Now you have " + remainingTasks + " tasks in the list\n");
    }

    public void listTasks(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.println(i + 1 + ". " + tasks.getTask(i));
        }
        System.out.println();
    }

    public void markTaskMessage(ArrayList<Task> tasks, int markedNum) {
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("  " + tasks.get(markedNum) + "\n");
    }

    public void unmarkTaskMessage(ArrayList<Task> tasks, int markedNum) {
        System.out.println("Oh no :( I've marked it as uncompleted: ");
        System.out.println("  " + tasks.get(markedNum) + "\n");
    }

    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    public void goodbyeMessage() {
        System.out.println("Bye good friend! Hope to see you again soon!\n");
    }



}
