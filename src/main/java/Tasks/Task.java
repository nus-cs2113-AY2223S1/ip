package Tasks;

/**
 * Represents a Task.
 * Contains the item description and completion status.
 */
public class Task {
    private String item;
    private boolean isCompleted;

    public Task(String item, boolean isCompleted) {
        this.item = item;
        this.isCompleted = isCompleted;
    }

    /**
     * Mark the task as Completed or Not Completed.
     *
     * @param isCompleted Set true to Complete and false otherwise.
     */
    public void setCompleted(Boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    /**
     * Returns the completion status of the Task.
     *
     * @return Status of the Task.
     */
    public boolean hasCompleted() {
        return isCompleted;
    }

    /**
     * Returns the task description.
     *
     * @return Description of Task.
     */
    public String getTaskName() {
        return item;
    }

    /**
     * Function only for overriding purposes by child classes.
     *
     * @return Empty String.
     */
    public String getTime() {
        return "";
    }

    /**
     * Returns the formatted description for the Task.
     * Format: [ ][{Done}] {description}
     * - Done: X or {emptySpace}
     *
     * @return Formatted String.
     */
    public String getCompleteDescription() {
        String output = "[ ][";
        output += (hasCompleted() ? "X] " : " ] ");
        output += getTaskName() + "\n";
        return output;
    }
}
