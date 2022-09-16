package duke;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + by + ")";
    }

    @Override
    public String convertToFileFormat() {
        String isDoneValue = (this.isDone) ? "1" : "0";
        String taskDetails = ("D|" + isDoneValue + "|" + this.task + "|" + this.by +"\n");
        return taskDetails;
    }
}
