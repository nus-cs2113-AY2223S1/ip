package duke.task;

import java.util.ArrayList;

import duke.exception.DukeException;
import duke.exception.InvalidTaskNumberDukeException;

/**
 * Represents and manages ArrayList of tasks
 */
public class TaskList {

    public ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Adds new task to ArrayList of tasks
     *
     * @param taskType  type of new task
     * @param arguments user input containing task name, and task date & time if applicable
     * @return string description of task for printing
     * @throws DukeException if task name or task date & time is invalid
     */
    public String addTask(TaskType taskType, String arguments) throws DukeException {
        switch (taskType) {
        case TODO:
            tasks.add(new ToDo(arguments));
            break;
        case EVENT:
            tasks.add(new Event(arguments));
            break;
        case DEADLINE:
            tasks.add(new Deadline(arguments));
            break;
        default:
            break;
        }
        return tasks.get(tasks.size() - 1).listTask(tasks);
    }

    /**
     * Mark task as done
     * Checks that task number is valid
     *
     * @param arguments user input containing task number
     * @return string description of the task after marking, for printing
     * @throws DukeException if task number is invalid
     */
    public String markAsDone(String arguments) throws DukeException {
        int taskNumber = TaskNumberParser.extractTaskNumber(arguments);
        checkTaskNumberValid(taskNumber);
        Task task = tasks.get(taskNumber - 1);
        task.markAsDone();
        return task.listTask(tasks);
    }

    /**
     * Mark task as not done
     * Checks that task number is valid
     *
     * @param arguments user input containing task number
     * @return string description of the task after unmarking, for printing
     * @throws DukeException if task number is invalid
     */
    public String markAsNotDone(String arguments) throws DukeException {
        int taskNumber = TaskNumberParser.extractTaskNumber(arguments);
        checkTaskNumberValid(taskNumber);
        Task task = tasks.get(taskNumber - 1);
        task.markAsNotDone();
        return task.listTask(tasks);
    }

    /**
     * Deletes a task from ArrayList of tasks
     * Checks that task number is valid
     *
     * @param arguments user input containing task number
     * @return string description of the task before deleting, for printing
     * @throws DukeException if task number is invalid
     */
    public String deleteTask(String arguments) throws DukeException {
        int taskNumber = TaskNumberParser.extractTaskNumber(arguments);
        checkTaskNumberValid(taskNumber);
        String listTask = tasks.get(taskNumber - 1).listTask(tasks);
        tasks.remove(taskNumber - 1);
        return listTask;
    }

    /**
     * Checks if task number is valid
     *
     * @param taskNumber task number specified by user
     * @throws DukeException if task number is out of bounds of taskList
     */
    public void checkTaskNumberValid(int taskNumber) throws DukeException {
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw new InvalidTaskNumberDukeException();
        }
    }

    /**
     * Directly adds a task to ArrayList of tasks
     *
     * @param task loaded task from save file
     */
    public void loadTask(Task task) {
        tasks.add(task);
    }
}
