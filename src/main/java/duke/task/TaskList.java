package duke.task;

import java.util.ArrayList;

import duke.exception.DukeException;
import duke.exception.InvalidTaskNumberDukeException;

/**
 * Represents ArrayList of tasks
 */
public class TaskList {

    public static ArrayList<Task> Tasks;

    public TaskList() {
        Tasks = new ArrayList<>();
    }

    /**
     * Adds new task to taskList
     * @param taskType type of new task
     * @param arguments user input containing task name and task date & time if any
     * @return string description of task for printing
     * @throws DukeException if task name or task date & time is invalid
     */
    public static String addTask(TaskType taskType, String arguments) throws DukeException {
        switch (taskType) {
        case TODO:
            Tasks.add(new ToDo(arguments));
            break;
        case EVENT:
            Tasks.add(new Event(arguments));
            break;
        case DEADLINE:
            Tasks.add(new Deadline(arguments));
            break;
        default:
            break;
        }
        return Tasks.get(Tasks.size() - 1).listTask();
    }


    public static String markAsDone(int taskNumber) throws DukeException {
        checkTaskNumberValid(taskNumber);
        Task task = Tasks.get(taskNumber - 1);
        task.markAsDone();
        return task.listTask();
    }

    public static String markAsNotDone(int taskNumber) throws DukeException {
        checkTaskNumberValid(taskNumber);
        Task task = Tasks.get(taskNumber - 1);
        task.markAsNotDone();
        return task.listTask();
    }

    public static String deleteTask(int taskNumber) throws DukeException {
        checkTaskNumberValid(taskNumber);
        String listTask = Tasks.get(taskNumber - 1).listTask();
        Tasks.remove(taskNumber - 1);
        return listTask;
    }

    /**
     * Checks if task number is valid
     * @param taskNumber task number specified by user
     * @throws DukeException if task number is out of bounds of taskList
     */
    public static void checkTaskNumberValid(int taskNumber) throws DukeException {
        if (taskNumber < 1 || taskNumber > Tasks.size()) {
            throw new InvalidTaskNumberDukeException();
        }
    }

    /**
     * Adds a task to taskList
     * @param task loaded task from save file
     */
    public static void loadTask(Task task) {
        Tasks.add(task);
    }
}
