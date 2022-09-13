package duke.task;

public class Deadline extends Task{
    protected String by;
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }
    @Override
    public String getTaskInfo() {
        return "[D]" + super.getTaskInfo() + " (by: " + this.by + ")";
    }
    @Override
    public String toString () {
        int isDoneInNumber = this.isDone ? 1 : 0;
        return String.format("D | %d | %s | %s", isDoneInNumber, description, by) + System.lineSeparator();
    }
}
