package duke.task;

/**
 * Represents a Task object
 */
public abstract class Task {
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
     * Getter for task description
     * @return Task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Getter for the status icon to be printed in the task status message
     * @return Status icon
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Formats the task information to be displayed to the user
     * @return Formatted information of the task instance
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


    /**
     * Formats all attributes in the task to be saved into the data file
     * @return Task data in a format parse-able by the Storage class
     */
    public abstract String getTaskData();
}
