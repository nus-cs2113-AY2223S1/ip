package duke;

import duke.exception.DukeException;
import duke.exception.EmptyDescriptionException;
import duke.task.Task;

import java.util.ArrayList;

public class TaskManager {

    public static int getTaskId(String line) {
        int inputId = Integer.parseInt(line.replaceAll("[^0-9]", ""));    // gets the id
        return (inputId - 1);
    }

    public static String getTaskType(String line) {
        String[] breakLine = line.split(" ", 2);
        return breakLine[0];
    }

    public static String getTaskDetails(String line) throws DukeException {
        String[] breakLine = line.split(" ", 2);
        // if there is no task description
        if (breakLine.length == 1 || breakLine[1].isBlank()) {
            throw new EmptyDescriptionException("☹ OOPS!!! The description of a task cannot be empty.");
        }
        return breakLine[1];
    }

    public static void deleteTask(ArrayList<Task> tasks, String line) throws DukeException {
        int taskId = getTaskId(line);
        Task taskToBeDeleted = tasks.get(taskId);
        int taskSize = tasks.size() - 1;
        System.out.println("\t_____________________");
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t  " + taskToBeDeleted);

        if (taskSize == 1 || taskSize == 0) {
            System.out.println("\tNow you have " + taskSize + " task in the list.");
        } else {
            System.out.println("\tNow you have " + taskSize + " tasks in the list.");
        }

        System.out.println("\t_____________________");
        tasks.remove(taskId);
    }

    public static void printMark(ArrayList<Task> tasks, int taskId) {
        if (tasks.get(taskId).isDone) {
            System.out.println("\tThis task is already marked!");
        } else {
            System.out.println("\tNice! I've marked this task as done:");
            tasks.get(taskId).setDone(tasks.get(taskId).isDone);
            System.out.println("\t" + tasks.get(taskId).getStatusIcon() + tasks.get(taskId).getDescription());
        }
    }

    public static void markTask(ArrayList<Task> tasks, String line) throws DukeException {
        int taskId = getTaskId(line);
        printMark(tasks, taskId);
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

    public static void unmarkTask(ArrayList<Task> tasks, String line) throws DukeException {
        int taskId = getTaskId(line);
        printUnmark(tasks, taskId);
    }

    public static void printTaskList(ArrayList<Task> tasks) {
        System.out.println("\t_____________________");
        if (tasks.size() == 0) {
            System.out.println("\tThere is no task!");
        } else {
            int count = 1;
            System.out.println("\tHere are the tasks in your list:");
            for (Task task : tasks) {
                System.out.println("\t" + count + ". " + task);
                count += 1;
            }
        }
        System.out.println("\t_____________________");
    }

    public static void printTotalNumberOfItems(ArrayList<Task> tasks) {
        int total = tasks.size();
        if (total == 1) {
            System.out.println("\tYou have 1 task");
        } else {
            System.out.println("\tYou have " + total + " tasks!!!");
        }
    }
}
