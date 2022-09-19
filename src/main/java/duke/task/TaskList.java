package duke.task;

import java.util.ArrayList;

import duke.exception.DukeException;
import duke.exception.InvalidTaskNumberDukeException;

public class TaskList {

    public ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public int getTaskIndex(Task task) {
        return tasks.indexOf(task);
    }

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

    public String markAsDone(int taskNumber) throws DukeException {
        checkTaskNumberValid(taskNumber);
        Task task = tasks.get(taskNumber - 1);
        task.markAsDone();
        return task.listTask(tasks);
    }

    public String markAsNotDone(int taskNumber) throws DukeException {
        checkTaskNumberValid(taskNumber);
        Task task = tasks.get(taskNumber - 1);
        task.markAsNotDone();
        return task.listTask(tasks);
    }

    public String deleteTask(int taskNumber) throws DukeException {
        checkTaskNumberValid(taskNumber);
        String listTask = tasks.get(taskNumber - 1).listTask(tasks);
        tasks.remove(taskNumber - 1);
        return listTask;
    }

    public void checkTaskNumberValid(int taskNumber) throws DukeException {
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw new InvalidTaskNumberDukeException();
        }
    }

    public void loadTask(Task task) {
        tasks.add(task);
    }
}
