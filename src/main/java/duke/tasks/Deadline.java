package duke.tasks;

public class Deadline extends Task {

    public String getDeadline() {
        return by;
    }

    private String by;

    public static final String TASK_TYPE = "D";

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[" + getTaskType() + "]" + super.toString() + " (by: " + by + ")";
    }

    public String getTaskType() {
        return TASK_TYPE;
    }
}
