package duke.task;

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
    public String toOutputFileFormat() {
        String out = "deadline " + description + " /by " + by;
        if (this.isDone) {
            return (out + " X");
        } else {
            return (out + " O");
        }
    }
}
