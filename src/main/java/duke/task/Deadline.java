package duke.task;

/**
 * A class that represents a deadline.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructor for Deadline.
     *
     * @param description Description of this deadline.
     * @param by Due date of this deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Gets the due date of this deadline.
     *
     * @return Due date of this deadline.
     */
    public String getBy() {
        return by;
    }

    /**
     * Fixes how this deadline would look like if it were to be converted into a string
     * based on its description and due date.
     *
     * @return The string representation of this deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.getStatusIcon() + " " + super.getDescription() + " (by: " + by + ")";
    }
}
