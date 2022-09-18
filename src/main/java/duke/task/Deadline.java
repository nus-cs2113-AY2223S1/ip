package duke.task;

/**
 * Stores deadlines.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Initializes deadline object.
     *
     * @param description Description of the deadline.
     * @param by Due date and time of the deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }
}
