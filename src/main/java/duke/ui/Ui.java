package duke.ui;

// import duke.exception.DukeException;
import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    public Ui() {
        Scanner scanner = new Scanner(System.in);
    }

    private static final String TOP_HORIZONTAL_RULE = "\t_____________________";
    private static final String BOTTOM_HORIZONTAL_RULE = "\t_____________________\n";

    public static final String BYE_MESSAGE = "Bye. Hope to see you again soon!";

    // Wrong number input format
    public static final String WRONG_NUMBER_INPUT_FORMAT = "Please input the task number that you want to delete.";

    // Item not present
    public static final String ITEM_NOT_PRESENT = "There is no such item in your Task List.";

    public static void printSuccessfulAdd(ArrayList<Task> tasks) {
        System.out.println(TOP_HORIZONTAL_RULE);
        System.out.println("\t" + "Got it. I've added this task:");
        System.out.println("\t" + "added: " + tasks.get(tasks.size() - 1));
        System.out.println(BOTTOM_HORIZONTAL_RULE);
    }

    public static void printIntroMessage(ArrayList<Task> tasks) {
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

    // print missing todo exception message
    public static void showInvalidTodoInputExceptionMessage() {
        System.out.println("\t☹ OOPS!!! The todo input is invalid. Please follow this format.");
        System.out.println("\tExample: todo <return book>");

    }

    // print missing deadline exception message
    public static void showInvalidDeadlineInputExceptionMessage() {
        System.out.println("\t☹ OOPS!!! Your deadline input is invalid. Please follow this format.");
        System.out.println("\tExample: deadline <return book> /by <2020-12-12>");

    }

    // print missing event exception message
    public static void showInvalidEventInputExceptionMessage() {
        System.out.println("\t☹ OOPS!!! Your event input is incomplete. Please follow this format.");
        System.out.println("\tExample: event <borrow book> /at <library>");
    }

    

    

}
