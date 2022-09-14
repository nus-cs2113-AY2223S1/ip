package duke;

import duke.exception.DukeException;
import duke.task.Task;

public class TaskManager {

    public static void printSuccessfulAdd(Task[] tasks, int taskCount) {
        System.out.println("\t_____________________");
        System.out.println("\t" + "Got it. I've added this task:");
        System.out.println("\t" + "added: " + tasks[taskCount]);
        System.out.println("\t_____________________\n");
    }

    public static int getTaskId(String line) {
        int markId = Integer.parseInt(line.replaceAll("[^0-9]", ""));    // gets the id
        return (markId - 1);
    }

    public static String getTaskType(String line) {
        String[] breakLine = line.split(" ", 2);
        return breakLine[0];
    }

    public static String getTaskDetails(String line) throws DukeException {
        String[] breakLine = line.split(" ", 2);
        return breakLine[1];
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

    public static void unmarkTask(Task[] tasks, String line) throws DukeException {
        int taskId = getTaskId(line);
        printUnmark(tasks, taskId);
    }

    public static void printMark(Task[] tasks, int taskId) {
        if (tasks[taskId].isDone) {
            System.out.println("This task is already done!");
        } else {
            System.out.println("\tNice! I've marked this task as done:");
            tasks[taskId].setDone(tasks[taskId].isDone);
            System.out.println("\t" + tasks[taskId].getStatusIcon() + tasks[taskId].getDescription());
        }
    }

    public static void markTask(Task[] tasks, String line) throws DukeException {
        int taskId = getTaskId(line);
        printMark(tasks, taskId);
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
}
