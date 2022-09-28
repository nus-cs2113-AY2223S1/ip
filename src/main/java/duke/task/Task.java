package duke.task;

/**
 * A class that represents the different forms of tasks in Duke.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task.
     *
     * @param description The task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Sets the done state of this task.
     *
     * @param isDone Whether this task is to be marked as done.
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Gets the done state of this task.
     *
     * @return Whether this task is done.
     */
    public boolean getDone() {
        return isDone;
    }

    /**
     * Gets the status icon.
     *
     * @return [X] if isDone, [] otherwise.
     */
    public String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }

    /**
     * Gets the status in terms of 1 and 0.
     *
     * @return 1 if isDone, 0 otherwise.
     */
    public int getStatusInNumber() {
        return isDone ? 1 : 0;
    }

    /**
     * Gets task description
     *
     * @return Description of this task.
     */
    public String getDescription() {
        return description;
    }

}
