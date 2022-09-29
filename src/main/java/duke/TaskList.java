package duke;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.task.Task;

import static duke.Ui.*;

import java.util.ArrayList;

// contains the task list e.g., it has operations to add/delete tasks in the list
public class TaskList {

    private ArrayList<Task> tasks;

    public static void deleteTask(ArrayList<Task> tasks, String line) throws DukeException {
        int taskId = Parser.getTaskId(line);
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

    public static void markTask(ArrayList<Task> tasks, String line) throws DukeException {
        int taskId = Parser.getTaskId(line);
        printMark(tasks, taskId);
    }

    public static void unmarkTask(ArrayList<Task> tasks, String line) throws DukeException {
        int taskId = Parser.getTaskId(line);
        printUnmark(tasks, taskId);
    }

    // get the size of the task list
    public static int getTaskListSize(ArrayList<Task> tasks) {
        return tasks.size();
    }
}
