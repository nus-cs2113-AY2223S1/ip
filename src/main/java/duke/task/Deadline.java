package duke.task;

/**
 * Represents a Deadline in the Duke system.
 * A Deadline object is abbreviated as 'D',
 * and has attribute 'by', representing the due time.
 */
public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by.replace("by", "").trim();

    }

    public String getBy() {
        return by;
    }

    /**
     * Returns formatted string of this deadline.
     * @return deadline string.
     */
    @Override
    public String toString() {
        String str = super.toString().substring(prefex_length);
        return "[D]" + str + " (by: " + by + ")";
    }

}
