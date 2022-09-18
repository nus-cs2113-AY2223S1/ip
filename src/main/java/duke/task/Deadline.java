package duke.task;

/**
 * Represents a task with a deadline that the user wishes to complete.
 */
public class Deadline extends Task {
    private String deadline;
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }

    /**
     * Processes a task's attributes to produce a string that encapsulates the task information which includes its type, done status and description.
     *
     * @return A well-formatted string containing the task information.
     */
    @Override
    public String formattedInformation() {
        String divider = " | ";
        String information = "D" + divider + (isDone ? "1" : "0") + divider + this.description + divider + this.deadline;
        return information;
    }
}