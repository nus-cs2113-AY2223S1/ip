package duke;

import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

// deals with interactions with the user
public class Ui {

    public static String readInput() {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        return input;
    }

    private static final String TOP_HORIZONTAL_RULE = "\t_____________________";
    private static final String BOTTOM_HORIZONTAL_RULE = "\t_____________________\n";

    public static final String WRONG_NUMBER_INPUT_FORMAT = "Please input the task number that you want to delete.";

    public static final String ITEM_NOT_PRESENT = "There is no such item in your Task List.";

    public static void printIntroMessage(TaskList tasks) {
        System.out.println("\tHello! I'm Duke, your personal task manager!");
        System.out.println(TOP_HORIZONTAL_RULE);
        if (tasks.size() > 0) {
            System.out.println("\tHere are the current tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("\t" + (i + 1) + "." + tasks.get(i));
            }
        } else {
            System.out.println("You have no tasks in your list. Try adding some!");
        }
        System.out.println(BOTTOM_HORIZONTAL_RULE);
    }

    public static void printByeMessage() {
        System.out.println(TOP_HORIZONTAL_RULE);
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println(BOTTOM_HORIZONTAL_RULE);
    }

    public static void printSuccessfulAdd(TaskList tasks) {
        System.out.println(TOP_HORIZONTAL_RULE);
        System.out.println("\t" + "Got it. I've added this task:");
        System.out.println("\t" + "added: " + tasks.get(tasks.size() - 1));
        System.out.println(BOTTOM_HORIZONTAL_RULE);
    }

    public static void showInvalidTodoInputExceptionMessage() {
        System.out.println("\t☹ OOPS!!! The todo input is invalid. Please follow this format.");
        System.out.println("\tExample: todo <return book>");

    }

    public static void showInvalidDeadlineInputExceptionMessage() {
        System.out.println("\t☹ OOPS!!! Your deadline input is invalid. Please follow this format.");
        System.out.println("\tExample: deadline <return book> /by <2020-12-12>");

    }

    public static void showInvalidEventInputExceptionMessage() {
        System.out.println("\t☹ OOPS!!! Your event input is incomplete. Please follow this format.");
        System.out.println("\tExample: event <borrow book> /at <library>");
    }

    public static void printMark(TaskList tasks, int taskId) {
        System.out.println(TOP_HORIZONTAL_RULE);
        if (tasks.get(taskId).isDone) {
            System.out.println("\tThis task is already marked!");
        } else {
            System.out.println("\tNice! I've marked this task as done:");
            tasks.get(taskId).setDone(tasks.get(taskId).isDone);
            System.out.println("\t" + tasks.get(taskId).getStatusIcon() + tasks.get(taskId).getDescription());
        }
        System.out.println(BOTTOM_HORIZONTAL_RULE);
    }

    public static void printUnmark(ArrayList<Task> tasks, int taskId) {
        System.out.println(TOP_HORIZONTAL_RULE);
        if (!tasks.get(taskId).isDone) {
            System.out.println("\tThis task is already unmarked!");
        } else {
            System.out.println("\tOK, I've marked this task as not done yet:");
            tasks.get(taskId).setDone(tasks.get(taskId).isDone);
            System.out.println("\t" + tasks.get(taskId).getStatusIcon() + tasks.get(taskId).getDescription());
        }
        System.out.println(BOTTOM_HORIZONTAL_RULE);
    }

    public static void printTaskList(TaskList tasks) {
        System.out.println("\t_____________________");
        if (tasks.size() == 0) {
            System.out.println("\tThere is no task!");
        } else {
            System.out.println("\tHere are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("\t" + (i + 1) + "." + tasks.get(i));
            }
        }
        System.out.println("\t_____________________");
    }

    public static void printTotalNumberOfItems(ArrayList<Task> tasks) {
        int total = tasks.size();
        System.out.println(TOP_HORIZONTAL_RULE);
        if (total == 1) {
            System.out.println("\tYou have 1 task");
        } else {
            System.out.println("\tYou have " + total + " tasks!!!");
        }
        System.out.println(BOTTOM_HORIZONTAL_RULE);
    }

    public static void printUnknownCommand() {
        System.out.println(TOP_HORIZONTAL_RULE);
        System.out.println("\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        System.out.println("\tHere are the commands that you can use:");
        System.out.println("\t1. todo <task name>");
        System.out.println("\t2. deadline <task name> /by <yyyy-mm-dd>");
        System.out.println("\t3. event <task name> /at <location>");
        System.out.println("\t4. list");
        System.out.println("\t5. mark <task number>");
        System.out.println("\t6. unmark <task number>");
        System.out.println("\t7. delete <task number>");
        System.out.println("\t8. bye");
        System.out.println(BOTTOM_HORIZONTAL_RULE);
    }

	public static void IndexOutOfBoundsExceptionMessage() {
        // System.out.println(TOP_HORIZONTAL_RULE);
        System.out.println("\t☹ OOPS!!! The task number is invalid.");
        System.out.println(BOTTOM_HORIZONTAL_RULE);
    }
}
