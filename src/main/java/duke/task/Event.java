package duke.task;
/**
 * A representation of Event.
 */
public class Event extends Task {
    protected String at;
    /**
     * Constructor for Event.
     *
     * @param description The event description.
     * @param at The date of event.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }
    /**
     * Formats the event information to store in hard-drive.
     *
     * @return A formatted string of event information.
     */
    @Override
    public String formatTaskToStringToStore() {
        String STORE_DIVIDER = " | ";
        return "E" + STORE_DIVIDER + (isDone() ? "1" : "0") + STORE_DIVIDER + getDescription() + STORE_DIVIDER + at + "\n";
    }
    /**
     * Overrides toString method of Task to get string representation of Event.
     *
     * @return A string representation of Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
