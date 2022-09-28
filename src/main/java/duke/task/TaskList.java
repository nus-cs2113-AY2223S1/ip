package duke.task;

import java.util.ArrayList;

/**
 * A class that handles all the user's inputted tasks.
 */
public class TaskList {

    private static ArrayList<Task> tasks;
    private static int taskCount = 0;

    /**
     * Constructor for TaskList.
     *
     * @param tasks The list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        TaskList.tasks = tasks;
        TaskList.taskCount = tasks.size();
    }

    /**
     * Gets the number of tasks in the list.
     *
     * @return The number of tasks.
     */
    public static int getTaskCount() {
        return taskCount;
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
        taskCount++;
    }

    /**
     * Marks a task on the list.
     *
     * @param target The index of the task to be marked.
     */
    public void markTarget(int target) {
        tasks.get(target - 1).setDone(true);
    }

    /**
     * Unmarks a task on the list.
     *
     * @param target The index of the task to be unmarked.
     */
    public void unmarkTarget(int target) {
        tasks.get(target - 1).setDone(false);
    }

    /**
     * Deletes a task from the list.
     *
     * @param index The index of the task to be deleted.
     */
    public void deleteTask(int index) {
        tasks.remove(index - 1);
        taskCount--;
    }

    /**
     * Gets a copy of all the current tasks in the list.
     *
     * @return The list of all tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Gets the task of a specific index in the list.
     *
     * @param index The index of the task to get.
     * @return The task of that index in the list.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Searches through all the tasks in the list to find for tasks that have
     * descriptions containing a certain target word.
     *
     * @param target The word to be searched for.
     * @return The list of all tasks that have descriptions containing that word.
     */
    public ArrayList<Task> findInList(String target) {
        ArrayList<Task> found = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(target)) {
                found.add(task);
            }
        }
        return found;
    }
}
