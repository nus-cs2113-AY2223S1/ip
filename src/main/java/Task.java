/**
 * Represents a Task object
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for task
     * @param description Physical action the user wants to create the task for
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns name of task
     * @return String name of task
     */
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
     * Returns formatted information of the task instance
     * @return String Formatted information of the task instance
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(),this.description);
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
