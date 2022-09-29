package duke.task;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.parser.Parser;

import static duke.Ui.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

// contains the task list e.g., it has operations to add/delete tasks in the list
public class TaskList {

    ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

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

    public static void markTask(TaskList tasks, String line) throws DukeException {
        int taskId = Parser.getTaskId(line);
        printMark(tasks, taskId);
    }

    public static void unmarkTask(ArrayList<Task> tasks, String line) throws DukeException {
        int taskId = Parser.getTaskId(line);
        printUnmark(tasks, taskId);
    }

    // get size of task list
    public int size() {
        return tasks.size();
    }

    // get task from task list
    public Task get(int index) {
        return tasks.get(index);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    // list out all the tasks in the task list
}
