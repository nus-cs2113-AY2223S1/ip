package duke.data.task;

public class Deadline extends Task {
    private static final String TASK_TYPE = "[D]";
    private static final String TIME_PREFIX = " (by: ";
    private static final String TIME_POSTFIX = ")";
    private String dueBy;

    public Deadline(String title, String dueBy, boolean isDone) {
        super(title, isDone);
        this.dueBy = dueBy;
    }

    public String getDueBy() {
        return dueBy;
    }

    @Override
    public String getTaskDetails() {
        return TASK_TYPE + super.getTaskDetails() + TIME_PREFIX + getDueBy() + TIME_POSTFIX;
    }
}
