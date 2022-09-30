package Tasks;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Converts task to string.
     * @return Task in string.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Converts task to string to be saved.
     * @return String to be saved.
     */
    @Override
    public String toSave() {
        return "D," + super.toSave() + "," + by;
    }
}