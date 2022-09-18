package duke.tasks;

public class Deadlines extends Task {
    private String toBeDoneBy;

    public String getToBeDoneBy() {
        return toBeDoneBy;
    }

    public Deadlines(String taskName, String toBeDoneBy) {
        super(taskName);
        this.toBeDoneBy = toBeDoneBy;
        this.type = "D";
    }

    public String toString() {
        String done = this.getIsDone() ? "[X]" : "[ ]";
        return "[D]" + done + this.getTaskName() + " (by: " + toBeDoneBy + " )";
    }
}