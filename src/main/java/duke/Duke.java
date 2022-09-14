package duke;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.Todo;
import duke.task.Event;


import java.util.Scanner;

public class Duke {

    private static final int TASK_SIZE = 100;

    public static void printIntroMessage() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);

        Task[] tasks = new Task[TASK_SIZE];
        int taskCount = 0;

        printIntroMessage();

        do {
            line = in.nextLine();
            String type = TaskManager.getTaskType(line);

            if (type.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                return;
            }
            switch (type) {
            case "list":
                TaskManager.printTaskList(tasks, taskCount);
                break;
            case "mark":
                try {
                    TaskManager.markTask(tasks, line);
                } catch (NumberFormatException | DukeException e) {
                    System.out.println("Please input the task number that you want to mark.");
                }
                break;
            case "unmark":
                try {
                    TaskManager.unmarkTask(tasks, line);
                } catch (NumberFormatException | DukeException e) {
                    System.out.println("Please input the task number that you wanna unmark");
                }

                break;
            case "total":
                System.out.println(taskCount);
                break;
            default:
                switch (type) {
                case "todo":
                    try {
                        String details = TaskManager.getTaskDetails(line);
                        Task t = new Todo(details);
                        tasks[taskCount] = t;
                    } catch (ArrayIndexOutOfBoundsException | DukeException e) {
                        System.out.println("☹ OOPS!!! The description of a todo cannot be empty. Please tell me what you want to do");
                        System.out.println("Example: todo (return book)");
                        continue;
                    }

                    break;
                case "deadline":
                    try {
                        String details = TaskManager.getTaskDetails(line);
                        String[] breakBy = details.split("/by", 2);
                        String detail = breakBy[0];
                        String by = breakBy[1];
                        Task d = new Deadline(detail, by);
                        tasks[taskCount] = d;
                    } catch (ArrayIndexOutOfBoundsException | DukeException e) {
                        System.out.println("Please tell me when is the deadline.");
                        System.out.println("Example: deadline (return book) /by (Sunday)");
                        continue;
                    }
                    break;
                case "event":
                    try {
                        String details = TaskManager.getTaskDetails(line);
                        String[] breakAt = details.split("/at", 2);
                        String detail = breakAt[0];
                        String at = breakAt[1];
                        Task e = new Event(detail, at);
                        tasks[taskCount] = e;
                    } catch (ArrayIndexOutOfBoundsException | DukeException e) {
                        System.out.println("Please tell me when is the event.");
                        System.out.println("Example: event (borrow book) /at (library)");
                        continue;
                    }

                    break;
                default:
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    continue;
                }
                TaskManager.printSuccessfulAdd(tasks, taskCount);
                taskCount += 1;
                break;
            }
        } while (!line.equals("bye"));
    }
}


