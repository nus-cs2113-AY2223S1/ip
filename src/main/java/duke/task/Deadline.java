package duke.task;

/**
 * deadline class which inherits from task
 * deadlines need a date
 */
public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String taskToString() {
        return "D" + super.taskToString() + "|" + by;
    }
}
