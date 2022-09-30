package duke.task;

/**
 * Represents a Event in the Duke system.
 * A Event object is abbreviated as 'E',
 * and has attribute 'duration', representing the happening time.
 */
public class Event extends Task {
    protected String duration;

    public Event(String description, String duration) {
        super(description);
        this.duration = duration.replace("at", "").trim();
    }

    public String getDuration() {
        return duration;
    }

    /**
     * Returns formatted string of this event.
     * @return event string.
     */
    @Override
    public String toString() {
        String str = super.toString().substring(prefex_length);
        return "[E]" + str + " (at: " + duration + ")";
    }
}
