package task;

/**
 * A class representing an event.
 */
public class Event extends Task {
    protected String at;

    /**
     * Constructor for Event.
     *
     * @param description Description of this event.
     * @param at When this event is at.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Gets the date when the event is occurring.
     *
     * @return When the event is at.
     */
    public String getAt() {
        return at;
    }
    /**
     * Fixes how this deadline would look like if it were to be converted into a string
     * based on its description and occurring date
     *
     * @return The string representation of this event.
     */
    @Override
    public String toString() {
        return "[E]" + super.getStatusIcon() + " " + super.getDescription() + " (at: " + at + ")";
    }

}
