package duke.task;

/**
 * A representation of task.
 */
public class Task {
    private final String description;
    private boolean isDone;

    /**
     * Constructor for task.
     *
     * @param description The task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Sets the task as done.
     */
    public void setAsDone() {
        this.isDone = true;
    }

    /**
     * Sets the task as undone.
     */
    public void setAsUndone() {
        this.isDone = false;
    }

    /**
     * Gets the task description.
     *
     * @return The task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the tasks done status.
     *
     * @return true If it is done and otherwise.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Formats the task information to store in hard-drive.
     *
     * @return A formatted string of task information.
     */
    public String formatTaskToStringToStore() {
        String STORE_DIVIDER = " | ";
        return (isDone ? "1" : "0") + STORE_DIVIDER + description;
    }

    /**
     * Overrides toString method of Object to get string representation of Task.
     *
     * @return A string representation of Task.
     */
    @Override
    public String toString() {
        return "[" + (this.isDone ? "X" : " ") + "] " + this.description;
    }
}
