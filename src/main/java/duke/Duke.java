package duke;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

//    private static final int TASK_SIZE = 100;

    public static void printIntroMessage() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);

        ArrayList<Task> tasks = new ArrayList<>();

        int taskCount = 0;

        printIntroMessage();

        do {
            line = in.nextLine();
            String type = TaskManager.getTaskType(line);

            if (type.equals("bye")) {
                System.out.println("\tBye. Hope to see you again soon!");
                return;
            }
            switch (type) {
            case "list":
                TaskManager.printTaskList(tasks, taskCount);
                break;
            case "mark":
                try {
                    TaskManager.markTask(tasks, line);
                } catch (NumberFormatException e) {
                    System.out.println("\tPlease input the task number that you want to mark.");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("\tThere is no such item in your Task List.");
                }
                break;
            case "unmark":
                try {
                    TaskManager.unmarkTask(tasks, line);
                } catch (NumberFormatException e) {
                    System.out.println("\tPlease input the task number that you want to mark.");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("\tThere is no such item in your Task List.");
                }
                break;
            case "total":
                System.out.println(taskCount);
                break;
            case "todo":
                try {
                    String details = TaskManager.getTaskDetails(line);
                    Task t = new Todo(details);
                    tasks.add(t);
                    TaskManager.printSuccessfulAdd(tasks, taskCount);
                    taskCount += 1;
                } catch (ArrayIndexOutOfBoundsException | DukeException e) {
                    System.out.println("\t☹ OOPS!!! The description of a todo cannot be empty. Please tell me what you want to do");
                    System.out.println("\tExample: todo (return book)");
                }
                break;
            case "deadline":
                try {
                    String details = TaskManager.getTaskDetails(line);
                    String[] breakBy = details.split("/by", 2);
                    String detail = breakBy[0];
                    String by = breakBy[1];
                    Task d = new Deadline(detail, by);
                    tasks.add(d);
                    TaskManager.printSuccessfulAdd(tasks, taskCount);
                    taskCount += 1;
                } catch (ArrayIndexOutOfBoundsException | DukeException e) {
                    System.out.println("\tPlease tell me when is the deadline.");
                    System.out.println("\tExample: deadline (return book) /by (Sunday)");
                }
                break;
            case "event":
                try {
                    String details = TaskManager.getTaskDetails(line);
                    String[] breakAt = details.split("/at", 2);
                    String detail = breakAt[0];
                    String at = breakAt[1];
                    Task e = new Event(detail, at);
                    tasks.add(e);
                    TaskManager.printSuccessfulAdd(tasks, taskCount);
                    taskCount += 1;
                } catch (ArrayIndexOutOfBoundsException | DukeException e) {
                    System.out.println("\tPlease tell me when is the event.");
                    System.out.println("\tExample: event (borrow book) /at (library)");
                }
                break;
            default:
                System.out.println("\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } while (!line.equals("bye"));
    }

}


