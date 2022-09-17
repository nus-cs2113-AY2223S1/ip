package duke.task;
/**
 * A representation of Deadline.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructor for Deadline.
     *
     * @param description The deadline description.
     * @param by The due date.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }
    /**
     * Formats the deadline information to store in hard-drive.
     *
     * @return A formatted string of deadline information.
     */
    @Override
    public String formatTaskToStringToStore() {
        String STORE_DIVIDER = " | ";
        return "D" + STORE_DIVIDER + (isDone() ? "1" : "0") + STORE_DIVIDER + getDescription() + STORE_DIVIDER + by + "\n";
    }
    /**
     * Overrides toString method of Task to get string representation of Deadline.
     *
     * @return A string representation of Deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (due: " + by + ")";
    }
}
