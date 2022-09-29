package Tasks;

/**
 * Represents a Deadline Task, extended from Task class.
 * Contains item description and completion status, as well as time description.
 */
public class Deadline extends Task {
    private String time;

    public Deadline(String description, String time, boolean isCompleted) {
        super(description, isCompleted);
        this.time = time;
    }

    /**
     * Returns the Time description in String format.
     *
     * @return String time description.
     */
    @Override
    public String getTime() {
        return time;
    }

    /**
     * Returns the formatted description for the Deadline.
     * Format: [D][{Done}] {description} (by: {time})
     * - Done: X or {emptySpace}
     *
     * @return Formatted String.
     */
    @Override
    public String getCompleteDescription() {
        String output = "[D][";
        output += (hasCompleted() ? "X] " : " ] ");
        output += getTaskName() + " (by: " + getTime() + ")\n";
        return output;
    }
}
