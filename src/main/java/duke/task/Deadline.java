package duke.task;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.category = "D";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString().substring(3) + " (by: " + by + ")";
    }

    @Override
    public String getSavedString() {
        return "D | " + getStatusIcon() + " | " + description + " | " + by;
    }

}
