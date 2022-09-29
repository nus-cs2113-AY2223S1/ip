package duke.task;

/**
 * Represents a task that contains a description of the task and a boolean attribute to determine whether
 * the task is done or not done.
 */
public class Task {
    public String description;
    public boolean isDone;

    /**
     * Initializes the Task object.
     *
     * @param description Name of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Get the status icon of the task depending on whether it is done or not done.
     *
     * @return "X" if task is done. " " if task is not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsUndone() {
        isDone = false;
    }

    /**
     * Displays the task message.
     *
     * @return "[X/ ]Task Description"
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}