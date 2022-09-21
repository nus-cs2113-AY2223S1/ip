package duke.task;

public class Deadline extends Task {
    protected String by;

    /**
     * Constructor of Deadline class
     *
     * @param description Description of Deadline class
     * @param by By attribute of the deadline task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.category = "D";
    }

    /**
     * Printed output when Deadline is printed
     *
     * @return printed output
     */
    @Override
    public String toString() {
        return "[D]" + super.toString().substring(3) + " (by: " + by + ")";
    }

    /**
     * Saved output messaged when Deadline is saved
     *
     * @return saved message
     */
    @Override
    public String getSavedString() {
        return "D | " + getStatusIcon() + " | " + description + " | " + by;
    }

}
