/**
 * Represents a Task
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructor for task
     * @param description Physical action the user wants to create the task for
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Returns the status icon to be printed in the task status message
     * @return String Status icon
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks task as done
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks task as undone
     */
    public void markAsNotDone() {
        isDone = false;
    }
}
