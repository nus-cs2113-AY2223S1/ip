package duke.task;

/**
 * Represents a task.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs constructor for Task, initializes task description and mark status.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Retrieves task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Retrieves task mark status.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Sets task mark status.
     */
    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * Contains description and mark status of task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
