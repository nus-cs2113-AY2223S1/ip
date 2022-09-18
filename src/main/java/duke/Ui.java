package duke;
/**
 * Deals with interactions with the user,
 * takes in user input and handles user output
 */

import java.util.Scanner;

public class Ui {
    /**
     * Prints a line separator.
     */
    public static void drawLine() {
        System.out.println("__________________________________________________");
    }

    /**
     * Prints welcome message.
     */
    public static void showWelcome() {
        String logo =  " _____ __    _____ _____ _____\n"
                + "| __  |  |  |     |     |     |\n"
                + "| __ -|  |__|  |  |  |  | | | |\n"
                + "|_____|_____|_____|_____|_|_|_|\n";
        System.out.println("Hello from\n" + logo);
        drawLine();
        System.out.println("Hello! I'm Bloom, your personal task manager.");
        System.out.println();
        System.out.println("What can I do for you?");
        drawLine();
    }

    /**
     * Prints goodbye message.
     */
    public static void showGoodbye() {
        System.out.println("Bye. Hope to see you again!");
        drawLine();
    }

    /**
     * Reads in user input and returns it.
     * @return user input.
     */
    public static String readCommand() {
        Scanner in = new Scanner(System.in);
        String command = in.nextLine();
        return command;
    }

    /**
     * Prints all tasks currently in the list.
     * @param tasks List of tasks.
     * @param taskCounter Number of tasks.
     */
    public static void showList(TaskList tasks, int taskCounter) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCounter; ++i) {
            char taskTypeCharacter = tasks.getTasks().get(i).getType();
            System.out.println(i+1 + ".[" + taskTypeCharacter + "]" +
                    "[" + (tasks.getTasks().get(i).isDone() ? "X] " : " ] ")
                    + tasks.getTasks().get(i));
        }
    }

    /**
     * Prints the marked task, with an [X] indicating marked.
     * @param tasks List of tasks.
     * @param taskID User input of task ID.
     */
    public static void showMarked(TaskList tasks, int taskID) {
        System.out.println("Nice! I've marked this task as done:");
        char taskType = tasks.getTasks().get(taskID - 1).getType();
        System.out.println("[" + taskType + "]" + "[X] " + tasks.getTasks().get(taskID - 1));
    }

    /**
     * Prints the unmarked task, with a [ ] indicating unmarked.
     * @param tasks List of tasks.
     * @param taskID User input of task ID.
     */
    public static void showUnmarked(TaskList tasks, int taskID) {
        System.out.println("Okay, I've marked this task as not done yet:");
        char taskType = tasks.getTasks().get(taskID - 1).getType();
        System.out.println("[" + taskType + "]"+ "[ ] " + tasks.getTasks().get(taskID - 1));
    }

    /**
     * Prints the deleted task.
     * @param tasks List of tasks.
     * @param taskID User input of task ID.
     */
    public static void showDeleted(TaskList tasks, int taskID) {
        System.out.println("Noted. I've removed this task:");
        char taskType = tasks.getTasks().get(taskID - 1).getType();
        System.out.print("[" + taskType + "]");
        System.out.print("[" + (tasks.getTasks().get(taskID - 1).isDone() ? "X] " : " ] "));
        System.out.println(tasks.getTasks().get(taskID - 1));
        System.out.println("Now you have " + (tasks.getTaskCounter() - 1) + " tasks in the list.");
    }

    /**
     * Prints the added task.
     * @param task Task that has been added.
     * @param taskCounter Number of tasks.
     */
    public static void showAdded(Task task, int taskCounter) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  [" + task.getType() + "]" + "[ ] " + task);
        System.out.println("Now you have " + taskCounter + " tasks in your list.");
    }
    public static void showSearched(TaskList tasks, String keyword) {
        int foundSearches = 0;
        for (int i = 0; i < tasks.getTasks().size(); ++i) {
            if (tasks.getTasks().get(i).getName().contains(keyword)) {
                foundSearches++;
                if (foundSearches == 1) {
                    System.out.println("Here are the matching tasks in your list:");
                }
                char taskType = tasks.getTasks().get(i).getType();
                System.out.print(i+1 + ".[" + taskType + "]");
                System.out.print("[" + (tasks.getTasks().get(i).isDone() ? "X] " : " ] "));
                System.out.println(tasks.getTasks().get(i));
            }
        }
        if (foundSearches == 0) {
            System.out.println("There are no matching tasks in your list");
        }
    }
    public static void showInvalidInputError() {
        System.out.println("Sorry, I do not understand. Please try again.");
    }
    public static void showLoadingError() {
        System.out.println("Unable to load from designated text file properly");
        System.out.println("Let's start with a new empty list");
        System.out.println();
    }
    public static void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    public static void showInvalidFormat() {
        System.out.println("Task list format is invalid.");
    }
}
