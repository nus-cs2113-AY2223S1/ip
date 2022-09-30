package duke.data.task;

import duke.common.Configurations;
import duke.exceptions.TaskManagerException;
import duke.storage.LocalStorage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Stores tasks and manages task-list.
 */
@SuppressWarnings({"FieldMayBeFinal", "unused", "PatternVariableCanBeUsed", "StatementWithEmptyBody"})
public class TaskManager {
    private ArrayList<Task> tasks;
    private String tasksPath;
    private String tasksFilename;

    /**
     * Initializes task manager with empty task-list and default values.
     */
    public TaskManager() {
        this.tasks = new ArrayList<>();
        this.tasksPath = Configurations.LOCAL_STORAGE_TASKS_DIRECTORY_PATH;
        this.tasksFilename = Configurations.LOCAL_STORAGE_TASKS_FILENAME;
    }

    /**
     * Initializes task manager by loading stored tasks.
     *
     * @param tasksPath Path of the directory to load.
     * @param tasksFilename Filename of the tasks file.
     */
    public TaskManager(String tasksPath, String tasksFilename) {
        this.tasksPath = tasksPath;
        this.tasksFilename = tasksFilename;
        loadTasks();
    }

    /**
     * Returns list of task in task manager.
     *
     * @return List of tasks.
     */
    public ArrayList<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    /**
     * Returns list of tasks in task manager that occurs at a certain date.
     *
     * @return List of tasks.
     */
    public ArrayList<Task> getTasks(LocalDate date) {
        if (date == null) {
            return getTasks();
        }

        ArrayList<Task> tasksWithDate = new ArrayList<>();
        for (Task task : tasks) {
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                LocalDate byDate = deadline.getBy().toLocalDate();
                if (date.equals(byDate)) {
                    tasksWithDate.add(deadline);
                }
            } else if (task instanceof Event) {
                Event event = (Event) task;
                LocalDate startAtDate = event.getStartAt().toLocalDate();
                LocalDate endAtDate = event.getEndAt().toLocalDate();
                if (!date.isBefore(startAtDate) && !date.isAfter(endAtDate)) {
                    tasksWithDate.add(event);
                }
            } else {
                // Do nothing if the current task is not an instance of the objects in the clauses above;
            }
        }

        return tasksWithDate;
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
     * @param taskIndex Task index.
     * @return Task.
     * @throws TaskManagerException.TaskNotFoundException If task is not the task manager.
     */
    public Task getTask(int taskIndex) throws TaskManagerException.TaskNotFoundException {
        try {
            return tasks.get(taskIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskManagerException.TaskNotFoundException();
        }
    }

    /**
     * Gets task number for task in list of tasks.
     *
     * @param task Task.
     * @return Task index.
     */
    public int getTaskIndex(Task task) {
        return tasks.indexOf(task);
    }

    /**
     * Deletes a task from list of tasks.
     *
     * @param taskIndex Task index.
     * @return Task.
     * @throws TaskManagerException.TaskNotFoundException If task is not the task manager.
     */
    public Task deleteTask(int taskIndex) throws TaskManagerException.TaskNotFoundException {
        try {
            Task task = tasks.get(taskIndex);
            tasks.remove(taskIndex);

            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new TaskManagerException.TaskNotFoundException();
        }
    }

    /**
     * Find tasks in list of tasks that matches description.
     *
     * @param description Description of the task.
     * @return List of tasks that matches the description.
     */
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
     * @param taskIndex Task index.
     * @throws TaskManagerException.TaskNotFoundException If task is not the task manager.
     */
    public void markTaskAsCompleted(int taskIndex) throws TaskManagerException.TaskNotFoundException {
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
     * @param taskIndex Task index.
     * @throws TaskManagerException.TaskNotFoundException If task is not the task manager.
     */
    public void markTaskAsUncompleted(int taskIndex) throws TaskManagerException.TaskNotFoundException {
        try {
            Task task = tasks.get(taskIndex);
            task.setComplete(false);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskManagerException.TaskNotFoundException();
        }
    }

    /**
     * Save tasks in task-list.
     *
     * @throws TaskManagerException.TasksFileIOException If there was an error saving the tasks.
     */
    public void saveTasks() throws TaskManagerException.TasksFileIOException {
        try {
            LocalStorage.saveTasks(tasks, tasksPath, tasksFilename);
        } catch (IOException e) {
            throw new TaskManagerException.TasksFileIOException("The tasks could not be saved to disk.");
        }
    }

    /**
     * Load tasks into task-list.
     */
    public void loadTasks() {
        try {
            tasks = LocalStorage.loadTasks(tasksPath, tasksFilename);
        } catch (IOException e) {
            tasks = new ArrayList<>();
        }
    }
}
