package duke;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.task.Task;
import static duke.ui.Ui.*;

import java.util.ArrayList;

public class TaskList {

    public static void deleteTask(ArrayList<Task> tasks, String line) throws DukeException {
        int taskId = Parser.parseTaskId(line);
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

    // public static void printMark(ArrayList<Task> tasks, int taskId) {
    //     if (tasks.get(taskId).isDone) {
    //         System.out.println("\tThis task is already marked!");
    //     } else {
    //         System.out.println("\tNice! I've marked this task as done:");
    //         tasks.get(taskId).setDone(tasks.get(taskId).isDone);
    //         System.out.println("\t" + tasks.get(taskId).getStatusIcon() + tasks.get(taskId).getDescription());
    //     }
    // }

    public static void markTask(ArrayList<Task> tasks, String line) throws DukeException {
        int taskId = Parser.parseTaskId(line);
        printMark(tasks, taskId);
    }

    public static void unmarkTask(ArrayList<Task> tasks, String line) throws DukeException {
        int taskId = Parser.parseTaskId(line);
        printUnmark(tasks, taskId);
    }
}
