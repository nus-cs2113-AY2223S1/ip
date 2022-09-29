package duke.task;

/**
 * Represents a deadline that contains a description of the task, the due date of the deadline,
 * and a boolean attribute to determine whether the task is done or not done.
 */
public class Deadline extends Task {
    public String by;

    /**
     * Initializes the Deadline object.
     *
     * @param description Name of the task.
     * @param by Due date of the deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Displays the deadline message.
     *
     * @return "[D][X/ ]Task Description (by: due date)"
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
