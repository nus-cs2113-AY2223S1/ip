public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + getStatusIcon() + super.description + " (by: " + this.by + ")";
    }

    public String getBy() {
        return this.by;
    }
}