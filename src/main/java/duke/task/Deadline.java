package duke.task;

public class Deadline extends Task {
    public String due;

    public Deadline(String taskName, String due) {
        super(taskName);
        this.due = due;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + due + ")";
    }
}