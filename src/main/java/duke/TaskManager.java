package duke;

import duke.exception.DukeException;
import duke.task.Task;

import java.util.ArrayList;

public class TaskManager {

    public static void printSuccessfulAdd(ArrayList<Task> tasks, int taskCount) {
        System.out.println("\t_____________________");
        System.out.println("\t" + "Got it. I've added this task:");
        System.out.println("\t" + "added: " + tasks.get(taskCount));
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

    public static void printUnmark(ArrayList<Task> tasks, int taskId) {
        if (!tasks.get(taskId).isDone) {
            System.out.println("\tThis task is already unmarked!");
        } else {
            System.out.println("\tOK, I've marked this task as not done yet:");
            tasks.get(taskId).setDone(tasks.get(taskId).isDone);
            System.out.println("\t" + tasks.get(taskId).getStatusIcon() + tasks.get(taskId).getDescription());
        }
    }

    public static void unmarkTask(ArrayList<Task> tasks, String line) {
        int taskId = getTaskId(line);
        printUnmark(tasks, taskId);
    }

    public static void printMark(ArrayList<Task> tasks, int taskId) {
        if (tasks.get(taskId).isDone) {
            System.out.println("\tThis task is already done!");
        } else {
            System.out.println("\tNice! I've marked this task as done:");
            tasks.get(taskId).setDone(tasks.get(taskId).isDone);
            System.out.println("\t" + tasks.get(taskId).getStatusIcon() + tasks.get(taskId).getDescription());
        }
    }

    public static void markTask(ArrayList<Task> tasks, String line) {
        int taskId = getTaskId(line);
        printMark(tasks, taskId);
    }

    public static void printTaskList(ArrayList<Task> tasks, int taskCount) {
        System.out.println("\t_____________________");
        // if there are no task
        if (tasks.size() == 0) {
            System.out.println("There are no tasks yet!");
        } else {
            System.out.println("\tHere are the tasks in your list:");
//            for (Task task: tasks){
//                System.out.println(task);
//            }
            for (int i = 0; i < taskCount;i++)
            {
                System.out.println("\t" + (i + 1) + ". " + tasks.get(i));
            }
        }
        System.out.println("\t_____________________");
    }
}
