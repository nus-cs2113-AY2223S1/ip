package duke;

import duke.task.Task;

import java.util.ArrayList;

/**
 * Represents the task-list to store tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Returns the tasks in the TaskList.
     *
     * @return tasks
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }


    /**
     * Returns the number of tasks in the TaskList.
     *
     * @return the number of tasks.
     */
    public int getTaskCount() {
        return tasks.size();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask (int index) {
        tasks.remove(index);
    }


}
