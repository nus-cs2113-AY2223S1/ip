package duke.task;

import duke.data.LocalStorage;

import java.io.IOException;
import java.util.ArrayList;

public class TaskManager {
    public final String DEFAULT_TASKS_PATH = "./data/";
    public final String DEFAULT_TASKS_FILENAME = "tasks.txt";

    private ArrayList<Task> tasks;
    private String tasksPath;
    private String tasksFilename;

    public TaskManager() {
        this.tasks = new ArrayList<>();
        this.tasksPath = DEFAULT_TASKS_PATH;
        this.tasksFilename = DEFAULT_TASKS_FILENAME;
    }

    public TaskManager(String tasksPath, String tasksFilename) {
        this.tasksPath = tasksPath;
        this.tasksFilename = tasksFilename;
        loadTasks();
    }

    /**
     * Prints all tasks in list to standard out.
     */
    public void printTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            int taskNumber = i + 1;
            System.out.print(taskNumber + ".");

            Task task = tasks.get(i);
            task.print();
        }
    }

    /**
     * Returns number of tasks in task manager.
     *
     * @return Number of tasks.
     */
    public int getNumTasks() {
        return tasks.size();
    }

    /**
     * Adds a task to list of tasks.
     *
     * @param task Task to add.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Gets a task from list of tasks.
     *
     * @param taskNumber Task number of task as shown by the function {@link #printTasks()}.
     * @return Task.
     * @throws TaskManagerException.TaskNotFoundException If task is not the task manager.
     */
    public Task getTask(int taskNumber) throws TaskManagerException.TaskNotFoundException {
        int taskIndex = taskNumber - 1;
        try {
            return tasks.get(taskIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskManagerException.TaskNotFoundException();
        }
    }

    public int getTaskNumber(Task task) {
        return tasks.indexOf(task) + 1;
    }

    /**
     * Deletes a task from list of tasks.
     *
     * @param taskNumber Task number of task as shown by the function {@link #printTasks()}.
     * @return Task.
     * @throws TaskManagerException.TaskNotFoundException If task is not the task manager.
     */
    public Task deleteTask(int taskNumber) throws TaskManagerException.TaskNotFoundException {
        int taskIndex = taskNumber - 1;
        try {
            Task task = tasks.get(taskIndex);
            tasks.remove(taskIndex);

            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new TaskManagerException.TaskNotFoundException();
        }
    }

    public ArrayList<Task> findTask(String description) {
        ArrayList<Task> matchingTasks = new ArrayList<>();

        for (Task task : tasks) {
            if (task.description.contains(description)) {
                matchingTasks.add(task);
            }
        }

        return matchingTasks;
    }

    /**
     * Marks a task as completed.
     *
     * @param taskNumber Task number of task as shown by the function {@link #printTasks()}.
     * @throws TaskManagerException.TaskNotFoundException If task is not the task manager.
     */
    public void markTaskAsCompleted(int taskNumber) throws TaskManagerException.TaskNotFoundException {
        int taskIndex = taskNumber - 1;
        try {
            Task task = tasks.get(taskIndex);
            task.setComplete(true);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskManagerException.TaskNotFoundException();
        }
    }

    /**
     * Marks a task as uncompleted.
     *
     * @param taskNumber Task number of task as shown by the function {@link #printTasks()}.
     * @throws TaskManagerException.TaskNotFoundException If task is not the task manager.
     */
    public void markTaskAsUncompleted(int taskNumber) throws TaskManagerException.TaskNotFoundException {
        int taskIndex = taskNumber - 1;
        try {
            Task task = tasks.get(taskIndex);
            task.setComplete(false);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskManagerException.TaskNotFoundException();
        }
    }

    public void saveTasks() throws TaskManagerException.TasksFileIOException {
        try {
            LocalStorage.saveTasks(tasks, tasksPath, tasksFilename);
        } catch (IOException e) {
            throw new TaskManagerException.TasksFileIOException("The tasks could not be saved to disk.");
        }
    }

    public void loadTasks() {
        try {
            tasks = LocalStorage.loadTasks(tasksPath, tasksFilename);
        } catch (IOException e) {
            tasks = new ArrayList<>();
        }
    }
}
