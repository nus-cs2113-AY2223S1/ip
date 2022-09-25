package duke.task;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by.replace("by ", "");
    }

    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        String str = super.toString().substring(prefex_length);
        return "[D]" + str + " (by: " + by + ")";
    }

}
