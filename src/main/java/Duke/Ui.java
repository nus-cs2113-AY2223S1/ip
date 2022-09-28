package Duke;

import Duke.Exceptions.TaskListEmptyException;
import Duke.Tasks.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    public static void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }
    public static void printGreeting() {
        printHorizontalLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printHorizontalLine();
    }

    public static String readInput() {
        Scanner s = new Scanner(System.in);
        return s.nextLine();
    }

    public static void printAddTaskText(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task.taskStatusWithDescriptionText());
        System.out.println("Now you have " + TaskList.getTasksListSize() + " tasks in the list.");
        Ui.printHorizontalLine();
    }

    public static void printTaskList() {
        try {
            if (TaskList.getTasksListSize() == 0) {
                throw new TaskListEmptyException();
            } else {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < TaskList.getTasksListSize(); i++) {
                    System.out.println((i + 1) + "." + TaskList.tasksList.get(i).taskStatusWithDescriptionText());
                }
            }
            Ui.printHorizontalLine();
        } catch (TaskListEmptyException e) {
            System.out.println(e);
        }
    }

    public static void printMarkTaskText(boolean newMark) {
        int taskNumber = TaskList.getTaskNumberOfInterest();
        String previousIcon = TaskList.tasksList.get(taskNumber).getStatusIcon();
        if (previousIcon == "X") {
            if (newMark) {
                System.out.println("This task has already been marked!");
            } else {
                System.out.println("OK, I've marked this task as not done yet:");
            }
        } else {
            if (!newMark) {
                System.out.println("This task has already been unmarked!");
            } else {
                System.out.println("Nice! I've marked this task as done:");
            }
        }
    }

    public static void printDeleteTaskText(Task task) {
        System.out.println("Noted. I've removed this task: ");
        System.out.println(task.taskStatusWithDescriptionText());
        System.out.println("Now you have " + TaskList.getTasksListSize() + " tasks in the list.");
        Ui.printHorizontalLine();
    }

    public static void printCorrectFormatText(String command) {
        switch(command) {
        case "todo":
            System.out.println("The command should be 'todo (task name)'.");
            break;
        case "deadline":
            System.out.println("The command should be 'deadline (task name) /by (deadline)'.");
            break;
        case "event":
            System.out.println("The command should be 'event (task name) /by (event date)'.");
            break;
        case "mark":
            System.out.println("The command should be 'mark (task number)'.");
            break;
        case "unmark":
            System.out.println("The command should be 'unmark (task number)'.");
            break;
        case "delete":
            System.out.println("The command should be 'delete (task number)'.");
            break;
        case "find":
            System.out.println("The command should be 'find (task)'.");
            break;
        default:
            System.out.println("Bob doesn't understand this command :<");
        }
    }

    public static void printUpdatedTaskMarkAndDescription() {
        int taskNumber = TaskList.getTaskNumberOfInterest();
        String newIcon = TaskList.tasksList.get(taskNumber).getStatusIcon();
        System.out.println("[" + newIcon + "] " + TaskList.tasksList.get(taskNumber).description);
    }

    public static void printTaskNumberNotIntegerError() {
        System.out.println("Task Number should be an integer!");
    }

    public static void printTasksWithKeyword(String keyword) {
        int count = 1;
        for (Task task : TaskList.tasksList) {
            if (task.description.contains(keyword)) {
                System.out.println(count + ". " + task.taskStatusWithDescriptionText());
                count += 1;
            }
        }
        if (count == 1 ) {
            System.out.println("There is no related task in your list.");
        }
    }

    public static void printGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
        printHorizontalLine();
    }
}
