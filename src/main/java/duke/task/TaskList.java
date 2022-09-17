package duke.task;

import duke.exception.DukeException;

import java.util.ArrayList;

/**
 * A representation of the list of tasks.
 */
public class TaskList {
    private final ArrayList<Task> taskList;

    /**
     * Constructor for TaskList.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Constructor for TaskList.
     *
     * @param taskList The current TaskList take from the stored file.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Gets the task given the index.
     *
     * @param index The index of the tasks to be found.
     * @return The corresponding task.
     * @throws DukeException If the index is out of bound.
     */
    public Task getTask(int index) throws DukeException {
        try {
            return taskList.get(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! The task number is out of bound ☹"
                    + "\nThere are only " + taskList.size() + " task(s) in your list");
        }
    }

    /**
     * Gets the size of the list.
     *
     * @return The size of the list.
     */
    public int getSize() {
        return taskList.size();
    }

    /**
     * Adds a new task to the list.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Marks a task as done given the index.
     *
     * @param index The index of the tasks to be marked as done.
     * @throws DukeException If the index is out of bound.
     */
    public void markDone(int index) throws DukeException {
        getTask(index).setAsDone();
    }
    /**
     * Marks a task as undone given the index.
     *
     * @param index The index of the tasks to be marked as undone.
     * @throws DukeException If the index is out of bound.
     */
    public void unmarkDone(int index) throws DukeException {
        getTask(index).setAsUndone();
    }

    /**
     * Deletes a task in the list given the index.
     *
     * @param index The index of the tasks to be deleted.
     * @throws DukeException If the index is out of bound.
     */
    public void delete(int index) throws DukeException {
        taskList.remove(getTask(index));
    }

    /**
     * Finds all matching tasks given a keyword.
     *
     * @param keyword The keyword contained in the tasks.
     * @return A list of matching tasks.
     */
    public TaskList findTasksByKeyword(String keyword) {
        TaskList result = new TaskList();
        for (Task task : taskList) {
            if (task.getDescription().contains(keyword)) {
                result.add(task);
            }
        }
        return result;
    }

    /**
     * Formats the list of tasks to store in hard-drive.
     *
     * @return A string represents the list of tasks.
     * @throws DukeException If there is an exception occurs.
     */
    public String formatTaskListToStringToStore() throws DukeException {
        StringBuilder formattedString = new StringBuilder();
        for (int i = 0; i < getSize(); i++) {
            formattedString.append(getTask(i + 1).formatTaskToStringToStore());
        }
        return formattedString.toString();
    }

    /**
     * Formats the result list of tasks after finding by keyword.
     *
     * @return A string represents the result list of tasks after finding.
     */
    public String toStringFindResult() {
        StringBuilder listString = new StringBuilder();
        if (taskList.size() == 0) {
            listString.append("There is no matching task in your list");
        } else {
            listString.append("Here are ").append(taskList.size()).append(" matching task(s) in your list:");
        }
        int index = 1;
        for (Task task : taskList) {
            listString.append('\n').append("   ").append(index++).append(". ").append(task);
        }
        return String.valueOf(listString);
    }

    /**
     * Overrides toString method of Object to get string representation of TaskList.
     *
     * @return A string representation of TaskList.
     */
    @Override
    public String toString() {
        StringBuilder listString = new StringBuilder();
        if (taskList.size() == 0) {
            listString.append("There is nothing in your list right now");
        } else {
            listString.append("Here are ").append(taskList.size()).append(" task(s) in your list:");
        }
        int index = 1;
        for (Task task : taskList) {
            listString.append('\n').append("   ").append(index++).append(". ").append(task);
        }
        return String.valueOf(listString);
    }
}
