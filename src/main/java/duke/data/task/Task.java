package duke.data.task;

import java.time.LocalDate;

/**
 * <code>Task</code> is the super class for every type of tasks in the application.
 */
public class Task {
    // The description of the task.
    private String taskName;
    // The completion status of the task.
    private boolean isDone;

    /**
     * Constructor of <code>Task</code>. The isDone boolean is set to false by default.
     *
     * @param taskName Description of the task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    /**
     * Returns the task description.
     *
     * @return Description of the task.
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Returns the completion status of task.
     *
     * @return A boolean value to represent whether a task is completed.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Stores the completion status of a task.
     *
     * @param done A boolean value to represent whether a task is completed.
     */
    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * Returns the completion status of the task as an icon.
     * "X" represents completed/done.
     * " " represents not completed/not done.
     *
     * @return A string icon of the task's completion status.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the formatted task details with task name and task completion status icon for listing.
     *
     * @return A string containing the formatted task details.
     */
    public String getTaskFullDetails() {
        return String.format("[%s] %s", getStatusIcon(), getTaskName());
    }

    /**
     * Returns the formatted task details with task name and task completion status icon for storing.
     *
     * @return A string containing the formatted task details.
     */
    public String getTaskInStorageFormat() {
        String format = String.format("%s | %s", this.isDone() ? "1" : "0", this.getTaskName());
        return format;
    }

    /**
     * Returns the date of the tasks.
     * To be overridden by tasks with datetime stored, for the purpose of task filtering with date.
     *
     * @return Null by default.
     */
    public LocalDate getTaskDate() {
        return null;
    }
}
