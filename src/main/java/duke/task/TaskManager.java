package duke.task;

import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> tasks;

    public TaskManager() {
        this.tasks = new ArrayList<>();
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
     * @return Number of tasks
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
     * Gets a task from list of tasks
     *
     * @param taskNumber Task number of task as shown by the function {@link #printTasks()}.
     * @return Task
     */
    public Task getTask(int taskNumber) {
        int taskIndex = taskNumber - 1;
        return tasks.get(taskIndex);
    }

    /**
     * Marks a task as completed.
     *
     * @param taskNumber Task number of task as shown by the function {@link #printTasks()}.
     */
    public void markTaskAsCompleted(int taskNumber) {
        int index = taskNumber - 1;
        tasks.get(index).setComplete(true);
    }

    /**
     * Marks a task as uncompleted.
     *
     * @param taskNumber Task number of task as shown by the function {@link #printTasks()}.
     */
    public void markTaskAsUncompleted(int taskNumber) {
        int index = taskNumber - 1;
        tasks.get(index).setComplete(false);
    }
}
