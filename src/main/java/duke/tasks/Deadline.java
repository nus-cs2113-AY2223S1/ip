package duke.tasks;

/**
 * Represent a task with description, status, and time of due specified.
 */
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
