package duke.task;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String formatTaskToStringToStore() {
        String STORE_DIVIDER = " | ";
        return "D" + STORE_DIVIDER + (isDone() ? "1" : "0") + STORE_DIVIDER + getDescription() + STORE_DIVIDER + by + "\n";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (due: " + by + ")";
    }
}
