package duke.ui;

import duke.task.Task;
import duke.tasklist.Tasklist;

import java.util.Scanner;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class Ui {

    private static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static final String DIVIDER = "-------------------------------------------------------------------------------";



    public Ui() {
    }


    public static void printLine() {
        System.out.println(DIVIDER);
    }

    public void start() {
        System.out.println(logo);
        printLine();
        System.out.println("Hello! I'm duke.Duke");
        System.out.println("What can i do for you?");
        printLine();
    }

    public void end() {
        printLine();
        System.out.println("Bye. Hope to see you again soon");
        printLine();
    }

    public String readCommand() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();

    }

    public static void showError(String message) {
        printLine();
        System.out.println(message);
        printLine();
    }

    public static void printList(Tasklist tasks) {
        printLine();
        System.out.println("Here are the tasks in your list:");
        int i = 1;
        for (Task l: tasks.tasks) {
            System.out.print(i + ".");
            System.out.println(l);
            i++;
        }
        printLine();
    }

    public static void printMarkTask(Task mark_task) {
        printLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(mark_task);
        printLine();
    }

    public static void printUnmarkTask(Task unmark_task) {
        printLine();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(unmark_task);
        printLine();
    }

    public static void printTask(Task task) {
        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + Tasklist.tasks.size() + " tasks in the list.");
        printLine();
    }

    public static void printDeletedTask(Task deleteTask) {
        printLine();
        System.out.println("Noted. I've removed this task: ");
        printTask(deleteTask);
        System.out.println("Now you have " + Tasklist.tasks.size() + " tasks in the list." );
        printLine();
    }

    public static void printFoundTask(ArrayList<Task> tasks) {
        int i = 1;
        printLine();
        System.out.println("Here are the matching tasks in your list: ");
        for (Task task: tasks) {
            System.out.println(i + "." + task);
            i++;
        }
        printLine();
    }


}


