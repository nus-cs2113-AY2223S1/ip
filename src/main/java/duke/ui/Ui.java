package duke.ui;

import duke.task.Task;
import duke.task.TaskList;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    static final String LOGO = "   _____                .__   \n" +
            "  /  _  \\ ___  ___ ____ |  |  \n" +
            " /  /_\\  \\\\  \\/  // __ \\|  |  \n" +
            "/    |    \\>    <\\  ___/|  |__\n" +
            "\\____|__  /__/\\_ \\\\___  >____/\n";

    public static void greetUser() {
        System.out.println(LOGO);
        String greeting = "____________________________________________________________\n"
                + "Hello! My name is Axel. :-)\n"
                + "How may I help you today?\n"
                + "____________________________________________________________";
        System.out.println(greeting);
    }

    public static void sayByeToUser() {
        String message = "____________________________________________________________\n"
                + "Goodbye. Hope to see you again soon!\n"
                + "____________________________________________________________";
        System.out.println(message);
    }

    public static void showLoadingError(String message) {
        System.out.println("Something went wrong trying to load the file: " + message);
    }

    public static String getUserCommand() {
        Scanner input = new Scanner(System.in);
        String command = input.nextLine();
        return command;
    }

    public void printTasks(TaskList taskList) {
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < taskList.getTasksCount(); i++){
            System.out.println((i+1) + "." + taskList.getTasks().get(i).toString());
        }
        System.out.println("____________________________________________________________");
    }

    public void printMatchingTasks(TaskList taskList) {
        System.out.println("____________________________________________________________");
        System.out.println("Here are the matching tasks in your list:");
        for(int i = 0; i < taskList.getTasksCount(); i++){
            System.out.println((i+1) + "." + taskList.getTasks().get(i).toString());
        }
        System.out.println("____________________________________________________________");
    }

    public static void printAccessTaskOutOfBoundsError() {
        String error = "    ____________________________________________________________\n" +
                "     ☹ OOPS!!! The task you have specified is out of bounds :-(\n";
        System.out.println(error);
    }

    public void printEmptyDescriptionError() {
        String error = "    ____________________________________________________________\n" +
                "     ☹ OOPS!!! The description cannot be empty.\n" +
                "    ____________________________________________________________";
        System.out.println(error);
    }

    public void printMissingTaskNumberError() {
        String error = "    ____________________________________________________________\n" +
                "     ☹ OOPS!!! You did not specify the task number :-(\n" +
                "    ____________________________________________________________";
        System.out.println(error);
    }

    public static void printTaskAlreadyMarkedError() {
        String error = "    ____________________________________________________________\n" +
                "     ☹ OOPS!!! The task you specified is already marked!\n" +
                "    ____________________________________________________________";
        System.out.println(error);
    }

    public static void printTaskAlreadyUnmarkedError() {
        String error = "    ____________________________________________________________\n" +
                "     ☹ OOPS!!! The task you specified is already unmarked!\n" +
                "    ____________________________________________________________";
        System.out.println(error);
    }

    public void printUnknownCommandError() {
        String error = "    ____________________________________________________________\n" +
                "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                "    ____________________________________________________________";
        System.out.println(error);
    }

    public static void printAddTaskAcknowledgement(ArrayList<Task> tasks, int tasksCount) {
        String acknowledgement = "____________________________________________________________\n"
                + "Got it. I've added this task:\n"
                + " " + tasks.get(tasksCount).toString();
        System.out.println(acknowledgement);
        tasksCount++;
        System.out.println("Now you have " + tasksCount + " task(s) in the list.");
        System.out.println("____________________________________________________________");
    }

    public static void printDeleteTaskAcknowledgement(int taskIndex, ArrayList<Task> tasks, int tasksCount) {
        String acknowledgement = "____________________________________________________________\n"
                + "Noted. I've removed this task:\n"
                + " " + tasks.get(taskIndex).toString();
        System.out.println(acknowledgement);
        tasksCount--;
        System.out.println("Now you have " + tasksCount + " task(s) in the list.");
        System.out.println("____________________________________________________________");
    }

    public static void printNumberOfTasks(int tasksCount) {
        System.out.println("You currently have " + tasksCount + " task(s) in your list.\n"
                + "    ____________________________________________________________");
    }

    public static void printMarkedTaskAsDoneAcknowledgement(int taskIndex, ArrayList<Task> tasks) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(taskIndex).toString());
        System.out.println("____________________________________________________________");
    }

    public static void printMarkedTaskAsUndoneAcknowledgement(int taskIndex, ArrayList<Task> tasks) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tasks.get(taskIndex).toString());
        System.out.println("____________________________________________________________");
    }
}
