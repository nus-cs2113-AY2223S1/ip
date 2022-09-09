
import java.util.Scanner;

public class Duke {

    public static String getInputType(String line) {
        String breakLine[] = line.split(" ", 2);
        return breakLine[0];
    }

    public static String getInputDetails(String line) {
        String breakLine[] = line.split(" ", 2);
        return breakLine[1];
    }

    public static void printTaskList(Task[] tasks, int taskCount) {
        System.out.println("\t_____________________");
        // if there are no task
        if (taskCount == 0) {
            System.out.println("There are no tasks yet!");
        } else {
            System.out.println("\tHere are the tasks in your list:");
            for (int i = 0; i < taskCount; i += 1) {
                System.out.println("\t" + (i + 1) + ". " + tasks[i]);
            }
        }
        System.out.println("\t_____________________");
    }

    public static int getTaskId(String line) {
        int markId = Integer.parseInt(line.replaceAll("[^0-9]", ""));    // gets the id
        return (markId - 1);
    }

    public static void printMark(Task[] tasks, int taskId) {
        // if the task is alrdy done
        //else
        if (tasks[taskId].isDone){
            System.out.println("This task is already done!");
        } else{
            System.out.println("\tNice! I've marked this task as done:");
            tasks[taskId].setDone(tasks[taskId].isDone);
            System.out.println("\t" + tasks[taskId].getStatusIcon() + tasks[taskId].getDescription());
        }
    }

    public static void printUnmark(Task[] tasks, int taskId) {
        if (!tasks[taskId].isDone) {
            System.out.println("this is already unmarked");
        } else {
            System.out.println("\tOK, I've marked this task as not done yet:");
            tasks[taskId].setDone(tasks[taskId].isDone);
            System.out.println("\t" + tasks[taskId].getStatusIcon() + tasks[taskId].getDescription());
        }
    }

    public static void printSuccessfulAdd(Task[] tasks, int taskCount) {
        System.out.println("\t_____________________");
        System.out.println("\t" + "Got it. I've added this task:");
        System.out.println("\t" + "added: " + tasks[taskCount]);
        System.out.println("\t_____________________\n");
    }

    public static void printIntroMessage() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    private static final int TASK_SIZE=100;


    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);

        Task[] tasks = new Task[TASK_SIZE];
        int taskCount = 0;

        printIntroMessage();

        do {
            line = in.nextLine();
            String type = getInputType(line);

            if (type.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                return;
            }
            if (type.equals("list")) {  // list out the items
                printTaskList(tasks, taskCount);
            } else if (type.equals("mark")) {
                try {
                    markTask(tasks, line);
                } catch (NumberFormatException e) {
                    System.out.println("Please input the task number that you wanna mark");
                }
            } else if (type.equals("unmark")) {
                try {
                    unmarkTask(tasks, line);
                } catch (NumberFormatException e) {
                    System.out.println("Please input the task number that you wanna unmark");
                }

            } else if (type.equals("total")) {
                System.out.println(taskCount);
            } else {
                if (type.equals("todo")) {
                    try {
                        String details = getInputDetails(line);
                        Task t = new Todo(details);
                        tasks[taskCount] = t;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("☹ OOPS!!! The description of a todo cannot be empty. Please tell me what you want to do");
                        System.out.println("Example: todo (return book)");
                        continue;
                    }

                } else if (type.equals("deadline")) {
                    try {
                        String details = getInputDetails(line);
                        String breakBy[] = details.split("/by", 2);
                        String detail = breakBy[0];
                        String by = breakBy[1];
                        Task d = new Deadline(detail, by);
                        tasks[taskCount] = d;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Please tell me when is the deadline.");
                        System.out.println("Example: deadline (return book) /by (Sunday)");
                        continue;
                    }
                } else if (type.equals("event")) {
                    String details = getInputDetails(line);
                    String breakAt[] = details.split("/at", 2);
                    String detail = breakAt[0];
                    String at = breakAt[1];
                    Task e = new Event(detail, at);
                    tasks[taskCount] = e;
                }
                printSuccessfulAdd(tasks, taskCount);
                taskCount += 1;
            }
        } while (!line.equals("bye"));
    }

    private static void unmarkTask(Task[] tasks, String line) {
        int taskId = getTaskId(line);
        printUnmark(tasks, taskId);
    }

    private static void markTask(Task[] tasks, String line) {
        int taskId = getTaskId(line);
        printMark(tasks, taskId);
    }
}
