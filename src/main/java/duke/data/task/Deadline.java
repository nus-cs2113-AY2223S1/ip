package duke.data.task;

public class Deadline extends Task {
    private String deadlineTime;

    public Deadline(String taskName, String deadlineTime) {
        super(taskName);
        this.deadlineTime = deadlineTime;
    }

    public String getDeadlineTime() {
        return deadlineTime;
    }

    @Override
    public String getTaskFullDetails() {
        return String.format("[D]%s (by: %s)",
                super.getTaskFullDetails(), this.getDeadlineTime());
    }
}
