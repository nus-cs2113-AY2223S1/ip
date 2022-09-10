package duke.task;

public class Deadline extends Task {
    private String dueBy;

    public Deadline(String title, String dueBy) {
        super(title);
        this.dueBy = dueBy;
    }

    public String getDueBy() {
        return dueBy;
    }

    @Override
    public String getTaskDetails() {
        return "[D]" + super.getTaskDetails() + " (by: " + getDueBy() + ")";
    }
}
