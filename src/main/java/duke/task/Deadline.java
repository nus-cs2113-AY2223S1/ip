package duke.task;

public class Deadline extends Task {
    protected String by;

    /**
     * Creates a new deadline object
     * 
     * @param description the description of the deadline
     * @param by          the date time of the deadline
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Formats how to print the deadline object
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
