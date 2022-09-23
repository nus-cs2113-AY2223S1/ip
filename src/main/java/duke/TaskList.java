package duke;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import duke.task.Task;

/**
 * The TaskList class handles operations on the list of tasks.
 */
public class TaskList {
    private static final String TASK_DOES_NOT_EXIST_ERROR_MESSAGE = "Task at index %d does not exist!";
    private static final String LIST_TASKS_HEADER = "Here are the tasks in your list:";
    List<Task> items;

    /**
     * Creates a tasklist from the default data file.
     */
    public TaskList() {
        this(Storage.readDataFile());
    }

    /**
     * Creates a tasklist from a list of tasks.
     * 
     * @param items List of tasks
     */
    public TaskList(List<Task> items) {
        this.items = new ArrayList<>(items);
    }

    /**
     * Number of tasks in the task list.
     * 
     * @return The number of tasks
     */
    public int size() {
        return items.size();
    }

    /**
     * Gets an item from the task list.
     * 
     * @param index The index (1-indexed) of the item.
     * @return The task at the given index.
     * @throws DukeException Throws an exception if there is no task at the given
     *                       index.
     */
    public Task getItem(int index) throws DukeException {
        if (index < 1 || index > items.size()) {
            throw new DukeException(String.format(TASK_DOES_NOT_EXIST_ERROR_MESSAGE, index));
        }
        return items.get(index - 1);
    }

    /**
     * Adds a new task to the task list.
     * 
     * @param item The task to be added
     * @throws DukeException Throws an exception if it fails to write the data to
     *                       the data file.
     */
    public void addItem(Task item) throws DukeException {
        items.add(item);
        Storage.writeDataFile(items);
    }

    /**
     * Removes a task from the task list.
     * 
     * @param task The task to be removed
     * @throws DukeException Throws an exception if it fails to write the data to
     *                       the data file.
     */
    public void deleteItem(Task task) throws DukeException {
        items.remove(task);
        Storage.writeDataFile(items);
    }

    /**
     * Marks a task as done.
     * 
     * @param task The task to be marked
     * @throws DukeException Throws an exception if it fails to write the data to
     *                       the data file.
     */
    public void markDone(Task task) throws DukeException {
        int index = items.indexOf(task);
        items.get(index).markDone();
        Storage.writeDataFile(items);
    }

    /**
     * Marks a task as undone.
     * 
     * @param task The task to be marked undone
     * @throws DukeException Throws an exception if it fails to write the data to
     *                       the data file.
     */
    public void markUndone(Task task) throws DukeException {
        int index = items.indexOf(task);
        items.get(index).markUndone();
        Storage.writeDataFile(items);
    }

    /**
     * Finds all tasks matching the given keyword and returns them in a task list.
     * 
     * @param keyword Keyword to use for the search
     * @return A new TaskList containing the tasks matching the keyword
     */
    public TaskList filter(String keyword) {
        final String searchKeyword = keyword.toLowerCase();
        List<Task> filteredItems = items.stream().filter(item -> item.getName().toLowerCase().contains(searchKeyword))
                .collect(Collectors.toList());
        return new TaskList(filteredItems);
    }

    /**
     * Formats the TaskList to be displayed.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(LIST_TASKS_HEADER);
        sb.append("\n");
        for (int i = 0; i < items.size(); i++) {
            sb.append(String.format("%d.%s\n", i + 1, items.get(i)));
        }
        return sb.toString();
    }
}
