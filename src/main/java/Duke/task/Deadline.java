package Duke.task;

/**
 * Deadline tasks are tasks that need to be done before a specific date/time
 */
public class Deadline extends Task {
    /**
     * Create new Deadline task
     * @param description Description of task
     * @param by Deadline for task to be done by
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.type = "D";
    }

    /**
     * Format the task as a String to display as output
     * @return Formatted String of the task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}