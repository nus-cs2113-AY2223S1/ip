package Duke.task;

/**
 * Task is an abstract class for tasks.
 */
public class Task {
    public String description;
    public boolean isDone;
    public String type;
    public String by;

    /**
     * Creates a new task
     * @param description Description of task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Formats the status of task to display as output
     * @return Formatted status of task
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done Duke.task with X
    }

    /**
     * Mark the task as done.
     */
    public void setDone() {
        isDone = true;
    }

    /**
     * Mark the task as undone.
     */
    public void setUnDone() {
        isDone = false;
    }

    /**
     * Format the task as a String to display as output
     * @return Formatted String of the task
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}