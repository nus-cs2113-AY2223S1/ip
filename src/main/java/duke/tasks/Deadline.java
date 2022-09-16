package duke.tasks;

public class Deadline extends Task {
    private String by;

    public Deadline(String taskDescription, String by) {
        super(taskDescription);
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    // Prints task
    @Override
    public String showTask() {
        return "[D][" + (isDone ? DONE_SYMBOL : " ") + "] " + taskDescription + "(by: " + by + ")";
    }
}
