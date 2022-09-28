package duke;

import java.util.ArrayList;
import java.util.Scanner;

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

    public void printWelcomeMessage() {
        System.out.println(LOGO + MESSAGE_WELCOME);
    }

    public void printDefaultTaskResponseMessage(int number, ArrayList<Task> tasks) {
        System.out.printf(MESSAGE_DEFAULT_TASK_RESPONSE, tasks.get(number - 1).toString(), tasks.size());
    }

    public void printGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void printTaskList(ArrayList<Task> tasks) {
        System.out.println("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + "." + tasks.get(i).toString());
        }
    }

    public void showLoadingError() {
        System.out.println("Error: Could not load file");
    }

    public void printEmptyTaskDescriptionMessage() {
        System.out.println("Uh oh, the description of a task cannot be empty. Try again.");
    }

    public void printInvalidCommandMessage() {
        System.out.println("You have entered an invalid command, please try again.");
    }

    public String getUserCommand() {
        String fullInputLine = in.nextLine();
        return fullInputLine;
    }

    public void printExitingMessage() {
        System.out.println("Exiting the program now.");
    }

    public void printTaskMarkedMessage() {
        System.out.println("Nice! I've marked this task as done:\n");
    }

    public void printMarkingTaskErrorMessage() {
        System.out.println("Error: Could not mark task.");
    }

    public void printUnmarkTaskMessage() {
        System.out.println("OK, I've marked this task as not done yet:\n");
    }

    public void printUnmarkTaskErrorMessage() {
        System.out.println("Error: Could not unmark task.");
    }

    public void printFileUpdatingErrorMessage() {
        System.out.println("Error: Could not unmark task.");
    }

    public void printDeleteTaskMessage(ArrayList<Task> tasks, int taskToDelete) {
        System.out.printf("Noted. I've removed this task:\n%s\nNow you have %d tasks in the list."
                , tasks.get(taskToDelete - 1).toString(), tasks.size() - 1);

    }

    public void printInvalidTaskMessage() {
        System.out.println("This task number is invalid, try again.");
    }

    public void printDeletingTaskErrorMessage() {
        System.out.println("Error: Task could not be deleted.");
    }

    public void printFilteredTaskList(ArrayList<Task> filteredTasks) {
        System.out.println("Here are the matching tasks in your list:\n");
        for (int i = 0; i < filteredTasks.size(); i++) {
            System.out.println(i + 1 + "." + filteredTasks.get(i).toString());
        }
    }
}
