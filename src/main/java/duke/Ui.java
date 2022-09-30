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

    public static void printIntroMessage(TaskList tasks) {
        System.out.println("\tHello! I'm Duke, your personal task manager!");
        System.out.println(TOP_HORIZONTAL_RULE);
        if (tasks.size() > 0) {
            System.out.println("\tHere are the current tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("\t" + (i + 1) + "." + tasks.get(i));
            }
        } else {
            System.out.println("\tYou have no tasks in your list. Try adding some!");
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
        System.out.println(TOP_HORIZONTAL_RULE);
        System.out.println("\tOOPS!!! The todo input is invalid. Please follow this format.");
        System.out.println("\tExample: todo <return book>");
        System.out.println(BOTTOM_HORIZONTAL_RULE);
    }

    public static void showInvalidDeadlineInputExceptionMessage() {
        System.out.println(TOP_HORIZONTAL_RULE);
        System.out.println("\tOOPS!!! Your deadline input is invalid. Please follow this format.");
        System.out.println("\tExample: deadline <return book> /by <2020-12-12>");
        System.out.println(BOTTOM_HORIZONTAL_RULE);
    }

    public static void showInvalidEventInputExceptionMessage() {
        System.out.println(TOP_HORIZONTAL_RULE);
        System.out.println("\tOOPS!!! Your event input is incomplete. Please follow this format.");
        System.out.println("\tExample: event <borrow book> /at <library>");
        System.out.println(BOTTOM_HORIZONTAL_RULE);
    }

    public static void printMark(TaskList tasks, int taskId) {
        if (tasks.get(taskId).isDone) {
            System.out.println(TOP_HORIZONTAL_RULE);
            System.out.println("\tThis task is already marked!");
            System.out.println(BOTTOM_HORIZONTAL_RULE);
        } else {
            System.out.println(TOP_HORIZONTAL_RULE);
            System.out.println("\tNice! I've marked this task as done:");
            tasks.get(taskId).setDone(tasks.get(taskId).isDone);
            System.out.println("\t" + tasks.get(taskId).getStatusIcon() + tasks.get(taskId).getDescription());
            System.out.println(BOTTOM_HORIZONTAL_RULE);
        }
    }

    public static void printUnmark(TaskList tasks, int taskId) {
        if (!tasks.get(taskId).isDone) {
            System.out.println(TOP_HORIZONTAL_RULE);
            System.out.println("\tThis task is already unmarked!");
            System.out.println(BOTTOM_HORIZONTAL_RULE);
        } else {
            System.out.println(TOP_HORIZONTAL_RULE);
            System.out.println("\tOK, I've marked this task as not done yet:");
            tasks.get(taskId).setDone(tasks.get(taskId).isDone);
            System.out.println("\t" + tasks.get(taskId).getStatusIcon() + tasks.get(taskId).getDescription());
            System.out.println(BOTTOM_HORIZONTAL_RULE);
        }
    }

    public static void printTaskList(TaskList tasks) {
        System.out.println(TOP_HORIZONTAL_RULE);
        if (tasks.size() == 0) {
            System.out.println("\tThere is no task!");
        } else {
            System.out.println("\tHere are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("\t" + (i + 1) + "." + tasks.get(i));
            }
        }
        System.out.println(BOTTOM_HORIZONTAL_RULE);
    }

    public static void printTotalNumberOfItems(TaskList tasks, int taskSize) {
        System.out.println(TOP_HORIZONTAL_RULE);
        if (taskSize == 1) {
            System.out.println("\tYou have 1 task");
        } else {
            System.out.println("\tYou have " + taskSize + " tasks!!!");
        }
        System.out.println(BOTTOM_HORIZONTAL_RULE);
    }

    public static void printUnknownCommand() {
        System.out.println(TOP_HORIZONTAL_RULE);
        System.out.println("\tOOPS!!! I'm sorry, but I don't know what that means :-(");
        System.out.println("\tHere are the commands that you can use:");
        System.out.println("\t1. todo <name_of_todo>");
        System.out.println("\t2. deadline <name_of_deadline> /by <yyyy-mm-dd>");
        System.out.println("\t3. event <name_of_event> /at <location>");
        System.out.println("\t4. list");
        System.out.println("\t5. mark <existing_task_number>");
        System.out.println("\t6. unmark <existing_task_number>");
        System.out.println("\t7. delete <existing_task_number>");
        System.out.println("\t8. find <keyword>");
        System.out.println("\t9. bye");
        System.out.println(BOTTOM_HORIZONTAL_RULE);
    }

    public static void printMatchingTasks(ArrayList<Task> matchingTasks) {
        System.out.println(TOP_HORIZONTAL_RULE);
        if (matchingTasks.size() == 0) {
            System.out.println("\tThere is no matching task!");
        } else {
            System.out.println("\tHere are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                System.out.println("\t" + (i + 1) + "." + matchingTasks.get(i));
            }
        }
        System.out.println(BOTTOM_HORIZONTAL_RULE);
    }

	public static void showInvalidFindDescriptionExceptionMessage() {
        System.out.println(TOP_HORIZONTAL_RULE);
        System.out.println("\tOOPS!!! Your find input is incomplete. Please follow this format.");
        System.out.println("\tExample: find <keyword>");
        System.out.println(BOTTOM_HORIZONTAL_RULE);
    }

	public static void showInvalidDeleteInputExceptionMessage() {
        System.out.println(TOP_HORIZONTAL_RULE);
        System.out.println("\tOOPS!!! Your number input is invalid or not found. Please follow this format and make sure item is present in list.");
        System.out.println("\tExample: delete <existing_task_number>");
        System.out.println(BOTTOM_HORIZONTAL_RULE);
    }

	public static void showInvalidMarkTaskInputExceptionMessage() {
        System.out.println(TOP_HORIZONTAL_RULE);
        System.out.println("\tOOPS!!! Your number input is invalid or not found. Please follow this format and make sure item is present in list.");
        System.out.println("\tExample: mark <existing_task_number>");
        System.out.println(BOTTOM_HORIZONTAL_RULE);
    }

    public static void showInvalidUnmarkTaskInputExceptionMessage() {
        System.out.println(TOP_HORIZONTAL_RULE);
        System.out.println("\tOOPS!!! Your number input is invalid or not found. Please follow this format and make sure item is present in list.");
        System.out.println("\tExample: unmark <existing_task_number>");
        System.out.println(BOTTOM_HORIZONTAL_RULE);
    }
}


