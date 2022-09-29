package duke.tasklist;

import java.util.ArrayList;
import java.util.List;

import duke.task.Task;

public class TaskList {
    private final List<Task> tasks;

    /**
     * Creates a new TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Creates a TaskList and propagates it with a list of Task objects.
     *
     * @param tasks A {@link List} containing {@link Task} objects to be included in the created TaskList.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * Returns the element at the specified index in the TaskList.
     *
     * @param index Index of the task to return.
     * @return The task at the specified position in the TaskList.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Inserts a new Task object to the end of the TaskList.
     *
     * @param task Task object to add to the list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes the task at the specified element in the TaskList.
     *
     * @param index Index of the task to delete.
     */
    public void delTask(int index) {
        tasks.remove(index);
    }

    /**
     * Returns the number of tasks in the TaskList.
     *
     * @return The number of elements in the TaskList
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the elements of the Task object in a List-compatible container for use in other functions.
     *
     * @return A {@link List} object containing the tasks in the TaskList.
     */
    public List<Task> asList() {
        return tasks;
    }

    /**
     * Searches the TaskList for a specified string and returns a new TaskList containing the result.
     *
     * @param searchTerm String to search for
     * @return A TaskList containing search results, if any
     */
    public TaskList findTasks(String searchTerm) {
        TaskList foundTasks = new TaskList();
        for (Task task : tasks) {
            if (task.getDescription().contains(searchTerm)) {
                foundTasks.addTask(task);
            }
        }
        return foundTasks;
    }

    /**
     * Returns a string representation of the TaskList object. The string consists of a list of the tasks, their descriptions,
     * and their status.
     *
     * @return String representation of the task list.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < size(); i++) {
            Task task = getTask(i);
            builder.append(String.format("%d.%s\n", i + 1, task));
        }
        return builder.toString();
    }
}
