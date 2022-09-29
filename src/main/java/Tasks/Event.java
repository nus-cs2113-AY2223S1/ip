package Tasks;

/**
 * Represents an Event Task, extended from Task class.
 * Contains item description and completion status, as well as time description.
 */
public class Event extends Task {
    private String time;

    public Event(String description, String time, boolean isCompleted) {
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
     * Returns the formatted description for the Event.
     * Format: [E][{Done}] {description} (at: {time})
     * - Done: X or {emptySpace}
     *
     * @return Formatted String.
     */
    @Override
    public String getCompleteDescription() {
        String output = "[E][";
        output += (hasCompleted() ? "X] " : " ] ");
        output += getTaskName() + " (at: " + getTime() + ")\n";
        return output;
    }
}
