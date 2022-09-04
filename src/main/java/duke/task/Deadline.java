package duke.task;

import duke.task.Task;

public class Deadline extends Task {
    private String dueBy;

    public Deadline(String description, String dueBy) {
        super(description);
        this.dueBy = dueBy;
    }

    @Override
    public String getTaskDetails() {
        return "[D]" + super.getTaskDetails() + " (by: " + dueBy + ")";
    }
}
