package duke.tasks;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getTaskType() {
        return "[D]";
    }

    @Override
    public String getAddedInfo() {
        return " (by: " + by + ")";
    }
}
